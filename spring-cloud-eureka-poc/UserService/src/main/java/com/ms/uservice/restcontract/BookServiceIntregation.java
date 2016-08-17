package com.ms.uservice.restcontract;

import com.ms.uservice.model.Book;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("http://book-service")
public interface BookServiceIntregation {

    @RequestMapping(value = "api/books/", method = RequestMethod.GET)
    List<Book> getAll();

    @RequestMapping(value = "api/books/", method = RequestMethod.POST)
    void add(@RequestBody Book book);

}
