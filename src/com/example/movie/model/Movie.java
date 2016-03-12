package com.example.movie.model;

public class Movie {
	
	private String title;
	private String desc;

	public Movie() {
	}
	
	public Movie(String title, String desc) {
		
		this.title = title;
		this.desc = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
