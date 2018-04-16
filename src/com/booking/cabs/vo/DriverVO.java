package com.booking.cabs.vo;

import java.util.List;

public class DriverVO {
	private long id;
	private double avgRating;
	private List<RatingHistoryVO> ratings;
	
	public DriverVO(long id, double avgRating, List<RatingHistoryVO> ratings) {
		super();
		this.id = id;
		this.avgRating = avgRating;
		this.ratings = ratings;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public List<RatingHistoryVO> getRatings() {
		return ratings;
	}
	public void setRatings(List<RatingHistoryVO> ratings) {
		this.ratings = ratings;
	}

	@Override
	public String toString() {
		return "DriverVO [id=" + id + ", avgRating=" + avgRating + ", ratings="
				+ ratings + "]";
	}
	
	
}
