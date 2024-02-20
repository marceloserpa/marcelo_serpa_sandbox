package com.marceloserpa.dop.search;

import java.util.ArrayList;
import java.util.Arrays;

public class BookService {

    public PageResult findByAuthor(String term){
        var bookRepository = new BookRepository();
        var result = bookRepository.findByAuthor(term);
        return switch (result) {
            case MatchResult.NoMatch() -> new PageResult(term, new ArrayList<>(), 0);
            case MatchResult.ExactMatch(var book) -> new PageResult(term, Arrays.asList(book), 1);
            case MatchResult.ManyMatch(var books) -> new PageResult(term, books, books.size());
        };
    }

}
