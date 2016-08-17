package com.ms.uservice.controller;

import com.ms.uservice.model.Book;
import com.ms.uservice.restcontract.BookServiceIntregation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users/")
public class UserController {

    @Autowired
    BookServiceIntregation bookService;

    @RequestMapping("{id}/my-books")
    public List<Book> getMyBooks(@PathVariable("id") Long id){
        return bookService.getAll();
    }

}
