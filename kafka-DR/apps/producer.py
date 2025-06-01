import uuid
import time
from kafka import KafkaProducer

bootstrap_servers = ['localhost:9092'] 

producer = KafkaProducer(
    bootstrap_servers=bootstrap_servers
)

message_id = str(uuid.uuid4())

for i in range(500):
    message = f"{message_id} => hello {i}"
    producer.send('hello-kafka', message.encode())
    producer.flush()
    time.sleep(5)
    print(f'Message {i} sent to cluster-a')