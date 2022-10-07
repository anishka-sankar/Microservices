package com.microservices.movieinfoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieResource {
	
	@Value("${api.key}")
	private String api_key;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{movieId}")
	public Movie movieInfo(@PathVariable("movieId") String id) {
		Movie m=restTemplate.getForObject("http://api.themoviedb.org/3/movie/upcoming" + "?api_key=" + api_key, Movie.class);
		return new Movie("1", "name", m.getOverview(), m.getTitle());
	}
}
