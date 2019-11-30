package com.marceloserpa.armeniapoc;

import com.linecorp.armeria.server.annotation.Param;


public class Book {

	@Param("title") 
    private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}