package org.com.marceloserpa.memory.softleaks;
public class Customer {

	private int id;
	private String name;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return id + " : " + name;
	}
	
	public Customer(String name) {
		super();
		this.name = name;
	}

	
}

