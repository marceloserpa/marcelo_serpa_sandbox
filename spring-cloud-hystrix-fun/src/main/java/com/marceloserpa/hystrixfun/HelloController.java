package com.marceloserpa.hystrixfun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	ExternalApiClient externalApiClient;

	@RequestMapping(value="/testfallback/{seconds}", method=RequestMethod.GET)
	public String hello(@PathVariable("seconds") Long seconds){
		return externalApiClient.myOperation(seconds);
	}
	
}
