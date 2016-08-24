package com.ms.bookservice.controller;

import com.ms.bookservice.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/books/")
public class BookController {

    private List<Book> books = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody Book book){
        System.out.println("inside method add");
        this.books.add(book);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getAll(){
        System.out.println("inside method gelAll");
        return this.books;
    }

    @RequestMapping(method = RequestMethod.GET, value = "print/{number}")
    public void printValue(@PathVariable("number") Integer number){
        System.out.println(String.format("Received %d !", number));
    }
}
