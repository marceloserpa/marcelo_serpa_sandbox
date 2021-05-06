package com.marceloserpa.dgspoc.book;

public class Book {
    private final String title;
    private final Integer releaseYear   ;

    public Book(String title, Integer releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }
}