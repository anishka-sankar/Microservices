package com.microservices.moviecatalog.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.microservices.moviecatalog.Rating;
import com.microservices.moviecatalog.RatingList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RatingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	/* Bulk Head 
	 @HystrixCommand(fallbackMethod = "getRatingsFallback", 
	 threadPoolKey="movieInfoPool",
	 threadPoolProperties={
	 	@HystrixProperty(name="coreSize", value="20"),
	 	@HystrixProperty(name="maxQueueSize",value="10")
	 })
	
	 */
	@HystrixCommand(fallbackMethod = "getRatingsFallback")
	public RatingList getRatings(@PathVariable String userId) {
		RatingList ratings=  restTemplate.getForObject("http://ratings-data-service/ratings/101", RatingList.class);
		
		return ratings;
	}
	
	public RatingList getRatingsFallback(@PathVariable String userId) {
		RatingList list=new RatingList();
		list.setRatings(Arrays.asList(new Rating("1", 3)));
	    return list ;
	}
}
