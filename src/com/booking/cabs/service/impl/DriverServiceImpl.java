package com.booking.cabs.service.impl;

import java.util.Date;
import java.util.List;

import com.booking.cabs.dao.CabsDao;
import com.booking.cabs.service.DriverService;
import com.booking.cabs.vo.DriverVO;
import com.booking.cabs.vo.RatingHistoryVO;

public class DriverServiceImpl implements DriverService{

	@Override
	public DriverVO addDriver(long id) {
		if(id == 0)
			id = new Date().getTime();
		DriverVO driverVO = new DriverVO(id, 0.0, null);
		CabsDao.saveDriver(driverVO);
		return driverVO;
	}

	@Override
	public DriverVO getDriver(long id) {
		return CabsDao.getDriver(id);
	}

	@Override
	public double getAvgRating(long id) {
		double avgRating = 0;
		DriverVO driverVO = getDriver(id);
		if(driverVO==null)
			return avgRating;
		List<RatingHistoryVO> ratingList = driverVO.getRatings();
		if(ratingList == null)
			return avgRating;
		double sum = 0;
		for(RatingHistoryVO ratingHistoryVO : ratingList){
			sum+=ratingHistoryVO.getRating();
		}
		avgRating = sum/ratingList.size();
		driverVO.setAvgRating(avgRating);
		CabsDao.saveDriver(driverVO);
		return avgRating;
	
	}
	
	
	public List<DriverVO> getTopRatedDriversList(int numberOfDrivers, double rating){
		List<DriverVO> driverList = CabsDao.getAllDrivers();
		if(driverList.isEmpty())
			return null;
		if(Double.compare(rating, 0)==0){
			if(numberOfDrivers==0||numberOfDrivers>=driverList.size())
				return driverList;
			return driverList.subList(0, numberOfDrivers);
		}
		for(int i =0 ;i<driverList.size();i++){
			if(Double.compare(driverList.get(i).getAvgRating(),rating)<0)
				driverList.remove(i);
		}
		
		if(numberOfDrivers==0||numberOfDrivers>=driverList.size())
			return driverList;
		return driverList.subList(0, numberOfDrivers);
	}
	
}
