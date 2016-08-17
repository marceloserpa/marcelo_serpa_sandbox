package com.ms.uservice.controller;

import com.ms.uservice.model.Book;
import com.ms.uservice.restcontract.BookServiceIntregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/")
public class UserController {

    @Autowired
    BookServiceIntregation bookService;

    @RequestMapping(value="{id}/my-books", method = RequestMethod.GET)
    public List<Book> getMyBooks(@PathVariable("id") Long id){
        return bookService.getAll();
    }

    @RequestMapping(value="{id}/my-books", method = RequestMethod.POST)
    public void addMyBooks(@RequestBody Book book){
        bookService.add(book);
    }


}
