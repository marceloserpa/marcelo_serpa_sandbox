# Spring Webflux JDBC

## Initialize Database

Run postgress database container:

```
sudo docker run --rm   --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data  postgres
```

Inside container, run:

```
psql -h localhost -U postgres -d postgres
```
Prepare table:

```
CREATE TABLE books(
   id serial PRIMARY KEY,
   title VARCHAR (255) NOT NULL,
   author VARCHAR (255) NOT NULL
);

```

## Run application

```
./gradlew clean build eclipse
java -jar build/libs/spring-webflux-jdbc-poc-0.1.0.jar
```


##	Rest operations
create book
```
curl --header "Content-Type: application/json" \
  	 --request POST \
  	 --data '{"title":"Doctor Sleep", "author":"Stephen King"}' \
  http://localhost:9000/books/ ;
```

delete book
```
curl --request DELETE localhost:9000/books/id/1
```