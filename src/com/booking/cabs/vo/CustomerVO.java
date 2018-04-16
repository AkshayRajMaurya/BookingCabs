package com.booking.cabs.vo;

import java.util.List;

public class CustomerVO {
	private long cid;
	private double avgRating;
	private List<RatingHistoryVO> ratings;
	
	public CustomerVO(long cid, double avgRating, List<RatingHistoryVO> ratings) {
		super();
		this.cid = cid;
		this.avgRating = avgRating;
		this.ratings = ratings;
	}
	
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
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
		return "CustomerVO [cid=" + cid + ", avgRating=" + avgRating
				+ ", ratings=" + ratings + "]";
	}
	
	
}
