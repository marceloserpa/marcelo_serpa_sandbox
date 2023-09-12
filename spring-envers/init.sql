CREATE TABLE book (
    id serial PRIMARY KEY,
    title VARCHAR (50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    version INTEGER NOT NULL
);