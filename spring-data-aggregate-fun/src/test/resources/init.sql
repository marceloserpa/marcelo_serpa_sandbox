CREATE TABLE purchase_order (
    id serial PRIMARY KEY,
    shipping_address VARCHAR (50) NOT NULL
);

CREATE TABLE order_item (
    id serial PRIMARY KEY,
    quantity INTEGER NOT NULL,
    product VARCHAR (50) NOT NULL,
    purchase_order INTEGER NOT NULL
);

ALTER TABLE order_item
ADD CONSTRAINT fk_purchase_order FOREIGN KEY (purchase_order) REFERENCES purchase_order (id);

CREATE TABLE book (
    id serial PRIMARY KEY,
    title VARCHAR (50) NOT NULL
);

CREATE TABLE author (
    id serial PRIMARY KEY,
    name VARCHAR (50) NOT NULL
);

CREATE TABLE book_author (
    book INTEGER,
    author INTEGER
);
