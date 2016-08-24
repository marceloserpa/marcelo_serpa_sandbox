package com.ms.uservice.controller;

import com.google.common.collect.ImmutableList;
import com.ms.uservice.model.Book;
import com.ms.uservice.restcontract.BookServiceIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/")
public class UserController {

    @Autowired
    BookServiceIntegration bookService;

    @RequestMapping(value="hello", method = RequestMethod.GET)
    public void hello(){
        ImmutableList.of(0,1,2,3,4,5,6,7,8,9,10).forEach(bookService::print);
    }

    @RequestMapping(value="{id}/my-books", method = RequestMethod.GET)
    public List<Book> getMyBooks(@PathVariable("id") Long id){
        return bookService.getAll();
    }

    @RequestMapping(value="{id}/my-books", method = RequestMethod.POST)
    public void addMyBooks(@RequestBody Book book){
        bookService.add(book);
    }


}
