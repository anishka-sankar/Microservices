package com.microservices.moviecatalog;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
		
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		RatingList ratings=  getRatings(userId);
				
		
		return ratings.getRatings().stream().map(rating -> 
			getCatalog(rating)
		).collect(Collectors.toList());		
		
	}
	
	
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
	
	@HystrixCommand(fallbackMethod="getCatalogFallback")
	public CatalogItem getCatalog(Rating rating) {
		Movie m=restTemplate.getForObject("http://movie-info-service/movie/abc", Movie.class);
		
		return new CatalogItem(m.getMovieName(),"description",rating.getRating());
	}
	
	public CatalogItem getCatalogFallback(Rating rating) {
		return new CatalogItem("movie doesn't exist", "description", rating.getRating());
	}
	
	
}
