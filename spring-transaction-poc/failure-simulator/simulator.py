import subprocess
import time
import shlex
import random
import string
import json
import requests
import threading

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
    with open(log_file_path, 'a') as log_file:
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

def perform_post():
    try:
        key = random_string()
        print(f'Performing HTTP request key = {key}')
        url = 'http://localhost:8080/person'
        request_body = {'name': key, 'lastname': 'lastname ' + key}
        requests.post(url=url, json=request_body)
    except Exception as e:
        print(f"An unexpected error occurred: {e}")

def fetch_people():   
    return requests.get('http://localhost:8080/person')

def scenario(scenario_execution):
    start = time.time()
    print(f' ==== Running scenario {scenario_execution} ==== \n')

    print('Starting Java Application')
    exec_with_logging('java -jar ../build/libs/spring-transaction-poc-0.0.1-SNAPSHOT.jar', 'app.log')

    print('Waiting Java application start')
    time.sleep(5)

    for i in range(20):
        print(f' >> Creating thread {i}')
        time.sleep(1)
        t = threading.Thread(target=perform_post)
        t.start()

    wait_before_kill_time = random.randint(2, 5)
    time.sleep(wait_before_kill_time) # forcing different execution time
    print(f'Stop Kafka broker after {wait_before_kill_time}s')
    exec('docker-compose stop kafka')

    time.sleep(5)
    application_pid = get_pid_by_port(8080, 'java')
    print(f'Kill Java process PID = {application_pid}')
    exec(f'kill -9 {application_pid}')

    # Prepare for the next scenario
    time.sleep(5)
    print('Start Kafka broker')
    exec('docker-compose start kafka')

    time.sleep(5)

    end = time.time()

    print(f' ==== Completed scenario {scenario_execution} in {end - start} seconds ==== \n')


def simulation_start(scenarios):

    start = time.time()

    for n in range(scenarios):
        scenario(n)

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

    people =  fetch_people().json()

    found = 0
    for person in people:
        if events_from_kafka.__contains__(person['name']):
            found = found + 1
        else:
            print('{person} not found!!')

    from_database = len(people)
    
    print(f'API returned {from_database} \n')
    print(f'Found in Kafka topic {found}')

    application_pid = get_pid_by_port(8080, 'java')
    print(f'Kill Java process PID = {application_pid}')
    exec(f'kill -9 {application_pid}')

    end = time.time()

    print(f'Time Elapsed = {end - start} seconds')

if __name__ == "__main__":
    simulation_start(10)