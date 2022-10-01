package com.microservices.ratingsdataservice;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {
	
//	@GetMapping("/{movieId}")
	public Rating getRatingInfo(@PathVariable String movieId) {
		return new Rating("101",4.1);
	}
	
	@GetMapping("/{userId}")
	public RatingList getRatings(@PathVariable String userId){
		RatingList list=new RatingList();
		list.setRatings(Arrays.asList(new Rating("101",3.1), new Rating("102",5.6)));
		return list;
	}
}
