package com.mserpa.netflix.hystrix.poc.commands;

import java.util.Optional;

import com.mserpa.netflix.hystrix.poc.facade.FakeRestApi;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class SumCommandFailure extends HystrixCommand<Optional<Double>>{
	
	private final static Long SECONDS = 1L;
	private final Double a;
	private final Double b;
	private final Boolean throwsError;
	
	
	protected SumCommandFailure(Double a, Double b, Boolean throwsError) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SumCommandFailure"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1500)));
		this.a = a;
		this.b = b;
		this.throwsError = throwsError;
	}

	@Override
	protected Optional<Double> run() throws Exception {
		if(throwsError) throw new RuntimeException("Fail");
		Double result = new FakeRestApi().sum(a, b, SECONDS);
		return Optional.ofNullable(result);
	}
	
	@Override
	protected Optional<Double> getFallback() {
		return Optional.empty();
	}

}
