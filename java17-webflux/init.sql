CREATE TABLE books(
    id serial PRIMARY KEY,
    title VARCHAR (50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    price decimal(5,2) NOT NULL
);
