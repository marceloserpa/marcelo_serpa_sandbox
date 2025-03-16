import subprocess
import time
import shlex
import random
import string
import asyncio
import aiohttp
import json

from kafka import KafkaConsumer


def exec(command):
    commands = shlex.split(command)
    print(commands)
    process = subprocess.Popen(commands, stdout=subprocess.PIPE, universal_newlines=True)

    while True:
        output = process.stdout.readline()
        print(output.strip())
        return_code = process.poll()
        if return_code is not None:
            print('RETURN CODE', return_code)
            for output in process.stdout.readlines():
                print(output.strip())
            break
    
    return True

def exec_dev_null(command):
    commands = shlex.split(command)
    subprocess.Popen(commands, stdout=subprocess.DEVNULL, universal_newlines=True)
    return True 

def exec_with_logging(command, log_file_path):
    commands = shlex.split(command)
    
    # Open the log file in append mode to store logs
    with open(log_file_path, 'a') as log_file:
        # Run the command and redirect stdout and stderr to the log file
        subprocess.Popen(commands, stdout=log_file, stderr=log_file, universal_newlines=True)

    return True

def get_pid_by_port(port, process_name):
    command = f"lsof -i :{port} | grep {process_name} | awk '{{print $2}}'"
    result = subprocess.run(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)

    if result.returncode == 0:
        pid = result.stdout.strip()
        return pid if pid else None
    else:
        print(f"Error: {result.stderr.strip()}")
        return None

def random_string(): 
    length = 8
    random_string = ''.join(random.choices(string.ascii_letters + string.digits, k=length))
    return random_string

async def perform_post(url, request_body):
    try:
        async with aiohttp.ClientSession() as session:
            async with session.post(url, json=request_body) as response:
                if response.status == 200:
                    result = await response.text()
                    print(f"Response: {result}")
                else:
                    print(f"Failed to perform POST. HTTP Status: {response.status}")
                    result = await response.text()
                    print(f"Error Response: {result}")

    except Exception as e:
        print(f"An unexpected error occurred: {e}")

async def fetch_people():
    people = list()
    
    async with aiohttp.ClientSession() as session:
        async with session.get('http://localhost:8080/person') as response:
            result = await response.text()
            people = result 
    return people

async def scenario():
    print('Starting Java Application')
    exec_with_logging('java -jar ../build/libs/spring-transaction-poc-0.0.1-SNAPSHOT.jar', 'app.log')
    time.sleep(3)
    application_pid = get_pid_by_port(8080, 'java')

    print(application_pid)

    print('Waiting Java application start')
    time.sleep(5)

    key = random_string()
    print('Performing HTTP request key='+key)
    url = 'http://localhost:8080/person'
    request_body = {'name': key, 'lastname': 'lastname ' + key}

    task = asyncio.create_task(perform_post(url, request_body))
    time.sleep(9)
    
    print('Stop Kafka broker')
    exec('docker-compose stop kafka')
    time.sleep(10)
   
    print('Kill Java process')
    exec(f'kill -9 {application_pid}')

    time.sleep(1)
    print('Start Kafka broker')
    exec('docker-compose start kafka')

    time.sleep(5)

    await task


async def simulation_start(scenarios):

    print(">> Starting infrastructure <<")
    exec('docker-compose -f ../docker-compose.yaml up -d')

    for n in range(scenarios):
        print(f' ==== Running scenario {n} ==== \n')
        time.sleep(10)
        await scenario()

        print(f' ==== Completed scenario {n} ==== \n')

    print('>>> Creating report')
    consumer = KafkaConsumer('people', 
        group_id=None,
        bootstrap_servers='localhost:9092', 
        auto_offset_reset='earliest', 
        enable_auto_commit=False, 
        auto_commit_interval_ms=1000)

    events_from_kafka = set()

    try:
        while True:
            msg = consumer.poll(timeout_ms=1000)
            
            if not msg:
                print("No more messages to consume.")
                break

            for partition, messages in msg.items():
                for message in messages:
                    decoded_string = message.value.decode('utf-8')
                    print(decoded_string)
                    data_dict = json.loads(decoded_string)
                    event = json.loads(data_dict)

                    events_from_kafka.add(event['name'])

    finally:
        consumer.close()

    print(events_from_kafka)
    

    print("Restarting Java application")
    exec_with_logging('java -jar ../build/libs/spring-transaction-poc-0.0.1-SNAPSHOT.jar', 'app.log')
    time.sleep(5)

    people =  json.loads(await fetch_people())

    found = 0
    for person in people:
        if events_from_kafka.__contains__(person['name']):
            found = found + 1
        else:
            print('{person} not found!!')

    from_database = len(people)
    
    print(f'API returned {from_database} \n')
    print(f'Found in Kafka topic {found}')

    print('Destroying infrastructure')
    exec('docker-compose -f ../docker-compose.yaml down')
    time.sleep(2)


async def main():
    await asyncio.gather(simulation_start(10))

if __name__ == "__main__":
    asyncio.run(main())