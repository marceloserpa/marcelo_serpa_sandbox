package com.mserpa.netflix.hystrix.poc.commands;

import  static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

public class SumCommandTimeoutTest {

	@Test
	public void shouldCalculateCorrecty() {
		// Given
		Double a = 1D;
		Double b = 2D;
		Long seconds = 1L;
		
		// When 
		Optional<Double> result = new SumCommandTimeout(a, b, seconds).execute();
		
		// Then
		assertTrue(3D == result.get());		
	}

	@Test
	public void shouldInvokeFallbackWhenToExceedTimeout() {
		// Given
		Double a = 1D;
		Double b = 2D;
		Long seconds = 2L;
		
		// When 
		Optional<Double> result = new SumCommandTimeout(a, b, seconds).execute();
		
		// Then
		assertFalse(result.isPresent());		
	}
	
	@Test
	public void shouldInvokeFallbackWhenCommandIsFail() {
		// Given
		Double a = 1D;
		Double b = 2D;
		Long seconds = 2L;
		
		// When 
		Optional<Double> result = new SumCommandTimeout(a, b, seconds).execute();
		
		// Then
		assertFalse(result.isPresent());		
	}	
	
}
