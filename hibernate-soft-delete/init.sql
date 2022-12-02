

CREATE TABLE person (
    id serial PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    deleted BOOLEAN DEFAULT false
);