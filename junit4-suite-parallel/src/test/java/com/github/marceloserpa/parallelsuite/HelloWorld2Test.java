package com.github.marceloserpa.parallelsuite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class HelloWorld2Test {
	
	private HelloWorld2 helloWorld;
	
	@Before
	public void setup() {
		helloWorld = new HelloWorld2();
	}
	
	@Test
	public void sayATest() {
		assertEquals("A", helloWorld.sayA());
	}
	
	@Test
	public void sayANotNullTest() {
		assertNotNull(helloWorld.sayA());
	}
	
	@Test
	public void sayBTest() {
		assertEquals("B", helloWorld.sayB());
	}

}
