package com.marceloserpa.aggregatefun.books;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class Book {

    private @Id
    Long id;
    private String title;
    private Set<AuthorRef> authors = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(createAuthorRef(author));
    }

    private AuthorRef createAuthorRef(Author author) {

        Assert.notNull(author, "Author must not be null");
        Assert.notNull(author.id, "Author id, must not be null");

        AuthorRef authorRef = new AuthorRef();
        authorRef.author = author.id;
        return authorRef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<AuthorRef> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorRef> authors) {
        this.authors = authors;
    }
}