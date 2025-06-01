import argparse

from kafka import KafkaConsumer

def main(failover_enabled: bool):
    print(failover_enabled)
    cluster_servers='localhost:9092'
    topic='hello-kafka'

    if failover_enabled == True:
        print("Connection on Failover Cluster")
        cluster_servers='localhost:9093'
        topic='cluster-a.hello-kafka'  
    else:
        print("Connection on Primary Cluster")

    consumer = KafkaConsumer(topic,
        group_id='poc', 
        enable_auto_commit=True,
        auto_commit_interval_ms=5000,
        auto_offset_reset='latest',
        bootstrap_servers=cluster_servers)

    print("Starting ...")
    for msg in consumer:
        print("Processing ...")
        print (msg)


if __name__ == "__main__":

    parser = argparse.ArgumentParser(description="Kafka consumer poc")
    parser.add_argument(
        "--failover_enabled", 
        action="store_true"
    )
    args = parser.parse_args()
    
    main(args.failover_enabled)