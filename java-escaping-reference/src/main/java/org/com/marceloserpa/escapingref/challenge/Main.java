package org.com.marceloserpa.escapingref.challenge;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


public class Main {

	public static void main(String[] args) {

		
		System.out.println("---START OF PROBLEM 1---");
		//Print out the current exchange rates
		System.out.println("The current exchange rates are USD 1 = ");
		Price price = new Price(1.0);
		price.getRates().forEach( (k,v) -> System.out.println(k + " " + v));
		
		//PROBLEM 1 - can we change one of the rates?
		price.getRates().put("USD", 2d);		
		System.out.println("The current exchange rates are USD 1 = ");
		price.getRates().forEach( (k,v) -> System.out.println(k + " " + v));
		
		System.out.println("---END OF PROBLEM 1---");
		
		System.out.println("---START OF PROBLEM 2---");
		
		//Get all the books printed out
		BookCollection bc = new BookCollection();
		bc.printAllBooks();
		
		//PROBLEM 2 - can we change a book?
		Book emma = bc.findBookByName("Emma");
		emma.setPrice(999d);
		bc.printAllBooks();
		
		System.out.println("---END OF PROBLEM 2---");
		
		System.out.println("---START OF PROBLEM 3---");
		//Print out the price of the book Tom Jones
		Book book = bc.findBookByName("Tom Jones");
		System.out.println("Tom Jones costs USD " + book.getPrice());
		System.out.println("Tom Jones costs EUR " + book.getPrice().convert("EUR"));
		System.out.println("Tom Jones costs GBP " + book.getPrice().convert("GBP"));
		System.out.println("---END OF PROBLEM 3---");
		
		
	}
}
