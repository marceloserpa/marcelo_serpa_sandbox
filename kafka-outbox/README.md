# Outbox pattern using Debezium with Multi-tenancy support 


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


Run the following commands to create new users,note the headers point to different tenants. These tenants are in differents database instances.

```
curl -H "Content-Type: application/json" -H "tenantId: 1" --request POST --data '{"username":"marcelo"}'  http://localhost:8080/users
```

```
curl -H "Content-Type: application/json" -H "tenantId: 2" --request POST --data '{"username":"elon"}'  http://localhost:8080/users
```

Retrieve users from different tenant:

```
curl -H "tenantId:1" localhost:8080/users
```

```
curl -H "tenantId:2" localhost:8080/users
```