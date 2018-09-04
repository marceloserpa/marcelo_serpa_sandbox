package com.github.marceloserpa.parallelsuite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class HelloWorldTest {
	
	private HelloWorld helloWorld;
	
	@Before
	public void setup() {
		helloWorld = new HelloWorld();
	}
	
	@Test
	public void sayATest() {
		
		System.out.println("hello-1) Execute HelloWorldTest.sayATest");
		assertEquals("A", helloWorld.sayA());
	}
	
	@Test
	public void sayANotNullTest() {
		System.out.println("hello-2) Execute HelloWorldTest.sayANotNullTest");
		assertNotNull(helloWorld.sayA());
	}
	
	@Test
	public void sayBTest() {
		System.out.println("hello-3) Execute HelloWorldTest.sayBTest");
		assertEquals("B", helloWorld.sayB());
	}

}
