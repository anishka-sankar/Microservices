package com.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Controller
@ResponseBody
public class CircuitBreakerController {
	
	private Logger logger=LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name="sample-api", fallbackMethod = "fallBackMethod")
	//@CircuitBreaker(name="sample-api", fallbackMethod = "fallBackMethod")
	//@RateLimiter(name="default")
	@Bulkhead(name="default")
	public String sampleApi() {
		//logger.info("executed .....");
		//ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-api", String.class);
		return "Sample API";
		
		//return forEntity.getBody();
		
	}
	
	public String fallBackMethod(Exception e) {
		return "from fall back method ....";
	}
	
	
}
