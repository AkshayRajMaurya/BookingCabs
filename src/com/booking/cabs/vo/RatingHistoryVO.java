package com.booking.cabs.vo;

public class RatingHistoryVO {
	private long id;
	private double rating;
	public RatingHistoryVO(long id, double rating) {
		super();
		this.id = id;
		this.rating = rating;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "RatingHistoryVO [id=" + id + ", rating=" + rating + "]";
	}
	
	
}
