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

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		
		RatingList ratings=  restTemplate.getForObject("http://ratings-data-service/ratings/101", RatingList.class);
		
		
		
		return ratings.getRatings().stream().map(rating -> {
			Movie m=restTemplate.getForObject("http://movie-info-service/movie/abc", Movie.class);
			
		/*
		 Movie m= webClientBuilder.build().get().uri("http://localhost:8081/movie/abc", Movie.class)
				.retrieve().bodyToMono(Movie.class).block(); 
		*/
			
			return new CatalogItem(m.getMovieName(),"description",rating.getRating());
		}).collect(Collectors.toList());
			
	
		
		
	}
}
