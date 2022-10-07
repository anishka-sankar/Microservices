package com.microservices.movieinfoservice;

public class Movie {
	
	private String movieId;
	private String movieName;
	private String overview;
	private String title;
	
	
	public Movie(String movieId, String movieName, String overview, String title) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.overview = overview;
		this.title = title;
	}


	public Movie() {
		
	}
	
	
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
}
