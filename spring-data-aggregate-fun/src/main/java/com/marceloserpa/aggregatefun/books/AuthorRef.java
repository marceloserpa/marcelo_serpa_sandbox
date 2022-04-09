package com.marceloserpa.aggregatefun.books;

import org.springframework.data.relational.core.mapping.Table;

//represents the knowledge of the Book aggregate about the authors.

@Table("book_author")
public class AuthorRef {
    Long author;
}