package com.mserpa.netflix.hystrix.poc.facade;

public class FakeRestApi {
	
	public Double sum(Double a, Double b, Long seconds) {
		try {
			Thread.sleep(seconds * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return a + b;
	}

}
