# Outbox pattern using Debezium


## Infrastructure deployment


To deploy the infrastructure run the following commands:

```
cd infrastructure

docker-compose up -d
```

After container deployment being completed we need to setup the source connectors: 

```
./setup-connector.sh
```


## Test


Run the following commands to create new users:

```
curl -H "Content-Type: application/json" --request POST --data '{"username":"marcelo"}'  http://localhost:9090/users
```

```
curl -H "Content-Type: application/json" --request POST --data '{"username":"elon"}'  http://localhost:9090/users
```

Retrieve users 
```
curl" localhost:9090/users
```