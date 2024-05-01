CREATE TABLE person (
    id serial PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    lastname VARCHAR(50) NOT NULL
);

CREATE TABLE user (
    id serial PRIMARY KEY,
    tenant_id INTEGER,
    username VARCHAR (50) NOT NULL
);


create table outbox (
    id serial PRIMARY KEY,
    payload TEXT NOT NULL
)