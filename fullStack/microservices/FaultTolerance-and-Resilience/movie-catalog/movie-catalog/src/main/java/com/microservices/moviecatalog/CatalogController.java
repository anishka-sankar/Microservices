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

import com.microservices.moviecatalog.services.CatalogService;
import com.microservices.moviecatalog.services.RatingService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private RatingService ratingService;
		
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		RatingList ratings=  ratingService.getRatings(userId);
				
		
		return ratings.getRatings().stream().map(rating -> 
		catalogService.getCatalog(rating)
		).collect(Collectors.toList());		
		
	}
	
	
	
	
	
	
}
