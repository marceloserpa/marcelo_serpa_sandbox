package com.github.marceloserpa.parallelsuite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class AnotherHelloWorldTest {
	
	private AnotherHelloWorld anotherHelloWorld;
	
	@Before
	public void setup() {
		anotherHelloWorld = new AnotherHelloWorld();
	}
	
	@Test
	public void sayATest() {
		System.out.println("another-1) Execute AnotherHelloWorld.sayATest");
		assertEquals("A", anotherHelloWorld.sayA());
	}
	
	@Test
	public void sayANotNullTest() {
		System.out.println("another-2)Execute AnotherHelloWorld.sayANotNullTest");
		assertNotNull(anotherHelloWorld.sayA());
	}
	
	@Test
	public void sayBTest() {
		System.out.println("another-3)Execute AnotherHelloWorld.sayBTest");
		assertEquals("B", anotherHelloWorld.sayB());
	}

}
