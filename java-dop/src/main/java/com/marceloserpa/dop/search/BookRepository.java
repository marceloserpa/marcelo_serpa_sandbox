package com.marceloserpa.dop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {

    private List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
        this.books.add(new Book("The Shining", "Stephen King"));
        this.books.add(new Book("Carrie", "Stephen King"));
        this.books.add(new Book("Foundation", "Isaac Asimov"));
        this.books.add(new Book("Blade Runner", "Philip K Dick"));
    }

    MatchResult<Book> findByAuthor(String term) {
        List<Book> foundBooks = this.books.stream().filter(book -> book.author().contains(term)).collect(Collectors.toList());
        if(foundBooks.isEmpty()) {
            return new MatchResult.NoMatch<>();
        }

        if(foundBooks.size() == 1) {
            return new MatchResult.ExactMatch<>(foundBooks.get(0));
        }

        return new MatchResult.ManyMatch<>(foundBooks);
    }
}
