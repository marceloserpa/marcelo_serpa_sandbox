package com.mserpa.netflix.hystrix.poc.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

public class SumCommandFailureTest {
	
	@Test
	public void shouldCalculateCorrecty() {
		// Given
		Double a = 1D;
		Double b = 2D;
		Boolean throwsException = false;
		
		// When 
		Optional<Double> result = new SumCommandFailure(a, b, throwsException).execute();
		
		// Then
		assertTrue(3D == result.get());		
	}
	
	@Test
	public void shouldInvokeFallbackWhenThrowsException() {
		// Given
		Double a = 1D;
		Double b = 2D;
		Boolean throwsException = true;
		
		// When 
		Optional<Double> result = new SumCommandFailure(a, b, throwsException).execute();
		
		// Then
		assertFalse(result.isPresent());		
	}

}
