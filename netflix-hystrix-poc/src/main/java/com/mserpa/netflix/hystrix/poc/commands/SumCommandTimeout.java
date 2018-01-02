package com.mserpa.netflix.hystrix.poc.commands;

import java.util.Optional;

import com.mserpa.netflix.hystrix.poc.facade.FakeRestApi;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SumCommandTimeout extends HystrixCommand<Optional<Double>>{

	private final Double a;
	private final Double b;
	private final Long seconds;
	
	public SumCommandTimeout(Double a, Double b, Long seconds) {
		 super(Setter
				 .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SumCalc"))
	             .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1500)));
		this.a = a;
		this.b = b;
		this.seconds = seconds;
	}

	@Override
	protected Optional<Double> run() throws Exception {
		Double result = new FakeRestApi().sum(a, b, seconds);
		return Optional.ofNullable(result);
	}

	@Override
	protected Optional<Double> getFallback() {
		return Optional.empty();
	}
	
	

}
