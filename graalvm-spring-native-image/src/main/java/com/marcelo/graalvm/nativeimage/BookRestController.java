package com.marcelo.graalvm.nativeimage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRestController {


    @GetMapping("books")
    public List<Book> getAll(){
        return List.of(new Book("The Shining", "Stephen King"),
                new Book("Blade Runner", "Philip K Dick"));
    }


}
