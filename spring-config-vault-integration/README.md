
# Spring Cloud Config + vault integration


## configure local git repository

```
cd ~/Documents/
mkdir config-repository-poc
cd config-repository-poc/
git init .
touch dev-environment.properties
```

Add the property:

```
message = Hello world
```



## configure Vault


Start the vault

```
cd vault
docker-compose up -d
```

enter inside the container and configure it:

```
vault login
vault kv put secret/dev-environment foo=bar
```

## starting env

Run configserver application and after start configclient application


Call the message endpoint and you will see "Hello world":

```
 curl localhost:9000/message
```

Go to your local git repository and change the property message on a-bootiful-client.properties. You should call call referesh endpoint from client-application to sync properties values.

```
curl -X POST http://localhost:9000/actuator/refresh
curl localhost:9000/message
```

:D
