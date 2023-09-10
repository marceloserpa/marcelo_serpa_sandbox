package com.marceloserpa.springenvers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {


    @GetMapping("/books")
    public List<Book> getBooks(){
        return List.of(
           new Book("The Shining", "Stephen King")
        );
    }

}
