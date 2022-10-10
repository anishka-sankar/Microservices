package com.microservices.moviecatalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviecatalog.CatalogItem;
import com.microservices.moviecatalog.Movie;
import com.microservices.moviecatalog.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CatalogService {
	
	@Autowired
	private RestTemplate restTemplate;
	
/*	Hystrix Param configuration
        @HystrixCommand(fallbackMethod="getCatalogFallback",
			commandProperties={
		@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
		@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
		@HystrixProperty(name="circuitBreaker.errorThresholdPrecentage", value="50"),
		@HystrixProperty(name="circuitBreaker.sleepWindowInMilliSeconds", value="5000"),
		
		
	}) */
	@HystrixCommand(fallbackMethod="getCatalogFallback")
	public CatalogItem getCatalog(Rating rating) {
		Movie m=restTemplate.getForObject("http://movie-info-service/movie/abc", Movie.class);
		
		return new CatalogItem(m.getMovieName(),"description",rating.getRating());
	}
	
	public CatalogItem getCatalogFallback(Rating rating) {
		return new CatalogItem("movie doesn't exist", "description", rating.getRating());
	}
}
