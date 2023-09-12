package com.marceloserpa.springenvers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {


    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.getAll().stream()
                .map(bookEntity -> new Book(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthor()))
                .collect(Collectors.toList());
    }

}
