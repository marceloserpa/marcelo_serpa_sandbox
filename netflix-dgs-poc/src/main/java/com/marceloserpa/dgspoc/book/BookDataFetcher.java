package com.marceloserpa.dgspoc.book;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class BookDataFetcher {

    private final List<Book> books = List.of(
            new Book("The Shining", 1980),
            new Book("The Raven", 1845),
            new Book("Thus Spoke Zarathustra", 1883),
            new Book("The prince", 1532)
    );

    @DgsQuery
    public List<Book> getBooks(@InputArgument String titleFilter) {
        if(titleFilter == null) {
            return books;
        }

        return books.stream().filter(s -> s.getTitle().contains(titleFilter)).collect(Collectors.toList());
    }
}
