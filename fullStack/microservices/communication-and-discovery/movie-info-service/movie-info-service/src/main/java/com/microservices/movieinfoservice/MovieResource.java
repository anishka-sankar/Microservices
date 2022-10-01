package com.microservices.movieinfoservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieResource {
	
	@GetMapping("/{movieId}")
	public Movie movieInfo(@PathVariable("movieId") String id) {
		return new Movie("101","Cobra");
	}
}
