package com.marceloserpa.netty.poc;

import java.io.Serializable;

public class Notification implements Serializable{

	private static final long serialVersionUID = 1L;

	private String email;
	private String text;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
