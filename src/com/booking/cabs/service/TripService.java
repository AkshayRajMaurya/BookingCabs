package com.booking.cabs.service;

import java.util.List;

public interface TripService {
	public void addTrip(long cid, double driverRating, long driverId, double customerRating);
	public List<Long> getEligibleDriversList(long customerId, boolean online);
	public List<Long> getEligibleDriversList(long customerId);
}
