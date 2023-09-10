CREATE TABLE book (
    id serial PRIMARY KEY,
    title VARCHAR (50) NOT NULL,
    author VARCHAR(50) NOT NULL
);

insert into book(title, author) values ('The Shining', 'Stephen King');