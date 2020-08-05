package com.marceloserpa.armeniapoc;

import java.util.ArrayList;
import java.util.List;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.Produces;
import com.linecorp.armeria.server.annotation.RequestObject;

public class BookApi {

	
	private List<Book> books = new ArrayList<>();
	
	@Post("/books")
	public HttpResponse books(@RequestObject Book book) {
		books.add(book);		
		return HttpResponse.of(HttpStatus.OK, MediaType.JSON, books.toString());
	}

	@Get("/books")
	@Produces(value = "application/json")
	public List<Book> getBooks() {	
		return books;
	}
	
}