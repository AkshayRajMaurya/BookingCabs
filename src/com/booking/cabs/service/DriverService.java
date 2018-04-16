package com.booking.cabs.service;

import java.util.List;

import com.booking.cabs.vo.DriverVO;

public interface DriverService {
	public DriverVO addDriver(long id);
	public DriverVO getDriver(long id);
	public double getAvgRating(long id);
	public List<DriverVO> getTopRatedDriversList(int numberOfDrivers, double rating);
	
}
