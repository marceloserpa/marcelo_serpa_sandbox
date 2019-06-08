package com.mserpa.bookstore.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mserpa.bookstore.models.Book;

@RestController
public class BookStoreEndpoint {

	@RequestMapping(method = RequestMethod.GET, path = "/books/")
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<>();
		Book theShining = new Book();
		theShining.setTitle("The Shining");
		theShining.setAuthor("Stephen King");
		
		books.add(theShining);
		return books;
	}
	
}
