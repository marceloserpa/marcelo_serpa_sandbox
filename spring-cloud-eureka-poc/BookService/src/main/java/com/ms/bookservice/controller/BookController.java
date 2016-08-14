package com.ms.bookservice.controller;

import com.ms.bookservice.model.Book;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/books/")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody Book book){
        this.books.add(book);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAll(){
        return this.books;
    }
}
