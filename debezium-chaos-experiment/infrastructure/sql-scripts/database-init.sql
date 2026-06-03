CREATE TABLE users (
    id serial PRIMARY KEY,
    username VARCHAR (50) NOT NULL
);

create table outbox (
    id serial PRIMARY KEY,
    payload TEXT NOT NULL
)