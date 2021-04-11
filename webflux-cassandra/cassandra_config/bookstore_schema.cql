DROP KEYSPACE IF EXISTS bookstore;

CREATE KEYSPACE bookstore WITH replication = {
  'class': 'SimpleStrategy',
  'replication_factor': 3
};

USE bookstore;

CREATE TABLE bookEntity (
  id int PRIMARY KEY,
  title text,
  author text,
);