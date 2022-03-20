CREATE TABLE books(
    id int PRIMARY KEY,
    title VARCHAR (50) NOT NULL
);

CREATE SEQUENCE public.hibernate_sequence INCREMENT 1 START 1 MINVALUE 1;