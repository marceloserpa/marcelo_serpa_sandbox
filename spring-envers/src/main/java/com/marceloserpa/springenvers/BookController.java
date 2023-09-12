package com.marceloserpa.springenvers;


import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/books")
    public void create (@RequestBody Book book) {
        var entity= new BookEntity();
        entity.setTitle(book.title());
        entity.setAuthor(book.author());
        bookService.save(entity);
    }

    @PutMapping("/books/{id}")
    public void update(@RequestBody Book book, @PathVariable("id") Long id) {
        var entity = new BookEntity();
        entity.setAuthor(book.author());
        entity.setTitle(book.title());
        entity.setId(id);
        entity.setVersion(1L);
        bookService.update(entity);

    }

}
