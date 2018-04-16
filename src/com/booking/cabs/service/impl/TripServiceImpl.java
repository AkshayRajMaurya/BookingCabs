package com.booking.cabs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.booking.cabs.dao.CabsDao;
import com.booking.cabs.service.CustomerService;
import com.booking.cabs.service.DriverService;
import com.booking.cabs.service.TripService;
import com.booking.cabs.vo.CustomerVO;
import com.booking.cabs.vo.DriverVO;
import com.booking.cabs.vo.RatingHistoryVO;

public class TripServiceImpl implements TripService{

	@Override
	public void addTrip(long cid, double customerRating, long driverId,
			double driverRating) {
		driverRating = driverRating<0?0:driverRating;
		customerRating = customerRating<0?0:customerRating;
		driverRating = driverRating>5?5:driverRating;
		customerRating = customerRating>5?5:customerRating;
		CustomerService customerService = new CustomerServiceImpl();
		DriverService driverService = new DriverServiceImpl();

		CustomerVO customerVO = customerService.getCustomer(cid);
		DriverVO driverVO = driverService.getDriver(driverId);

		addDriverRating(driverVO, cid, customerRating);
		addCustomerRating(customerVO, driverId, driverRating);
	}

	@Override
	public List<Long> getEligibleDriversList(long customerId, boolean online){
		List<Long> eligibleDriversList = new ArrayList<>();
		List<DriverVO> driverList = CabsDao.getAllDrivers();
		DriverService driverService = new DriverServiceImpl();
		CustomerService customerService = new CustomerServiceImpl();
		CustomerVO customerVO = customerService.getCustomer(customerId);
		double customerRating = customerService.getAvgRating(customerId);
		for(DriverVO driver:driverList){
			if(!CabsDao.isDriverOnline(driver.getId())||driverService.getAvgRating(driver.getId())<customerRating)
				continue;
			eligibleDriversList.add(driver.getId());
		}
		if(!eligibleDriversList.isEmpty())
			return eligibleDriversList;

		for(DriverVO driver:driverList){
			if(hasGivenRide(driver, customerVO))
				eligibleDriversList.add(driver.getId());
		}

		return eligibleDriversList;
	}

	@Override
	public List<Long> getEligibleDriversList(long customerId){
		List<Long> eligibleDriversList = new ArrayList<>();
		List<DriverVO> driverList = CabsDao.getAllDrivers();
		DriverService driverService = new DriverServiceImpl();
		CustomerService customerService = new CustomerServiceImpl();
		CustomerVO customerVO = customerService.getCustomer(customerId);
		double customerRating = customerService.getAvgRating(customerId);
		for(DriverVO driver:driverList){
			if(driverService.getAvgRating(driver.getId())<customerRating)
				continue;
			eligibleDriversList.add(driver.getId());
		}
		if(!eligibleDriversList.isEmpty())
			return eligibleDriversList;

		for(DriverVO driver:driverList){
			if(hasGivenRide(driver, customerVO))
				eligibleDriversList.add(driver.getId());
		}

		return eligibleDriversList;
	}

	private boolean hasGivenRide(DriverVO driverVO , CustomerVO customerVO){
		List<RatingHistoryVO> customerRatingList = customerVO.getRatings();
		List<RatingHistoryVO> ratingHistoryList  = driverVO.getRatings();
		if(ratingHistoryList==null||ratingHistoryList.isEmpty()||customerRatingList==null||customerRatingList.isEmpty())
			return false;
		double driverRating = 0;
		int flag = 0;
		for(RatingHistoryVO list: customerRatingList){
			if(list.getId()==driverVO.getId()){
				driverRating = list.getRating();
				flag =1;
			}
		}
		if(flag ==0)
			return false;

		for(RatingHistoryVO list: ratingHistoryList){
			if(list.getId()==customerVO.getCid()&&Double.compare(1, list.getRating())!=0&&Double.compare(1, driverRating)!=0)
				return true;
		}
		return false;
	}

	private void addCustomerRating(CustomerVO customerVO, long driverId, double driverRating){
		List<RatingHistoryVO> customerRatingList = customerVO.getRatings();
		if(customerRatingList==null){
			customerRatingList = new ArrayList<RatingHistoryVO>();
			customerRatingList.add(new RatingHistoryVO(driverId, driverRating));
			//customerVO.setAvgRating(((customerVO.getAvgRating()*customerVO.getRatings().size())+driverRating)/customerVO.getRatings().size()+1);
			customerVO.setRatings(customerRatingList);
			customerVO.setAvgRating(((customerVO.getAvgRating()*customerVO.getRatings().size()-1)+driverRating)/customerVO.getRatings().size());
			CabsDao.saveCustomer(customerVO);
			return;
		}
		int added = 0;
		for(int i=0;i<customerRatingList.size();i++){
			RatingHistoryVO ratingVO = customerRatingList.get(0);
			if(ratingVO.getId()==driverId){
				customerRatingList.remove(i);
				customerRatingList.add(new RatingHistoryVO(driverId, driverRating));
				added = 1;
			}
			
		}
		if(added==0)
			customerRatingList.add(new RatingHistoryVO(driverId, driverRating));
		customerVO.setRatings(customerRatingList);
		customerVO.setAvgRating(((customerVO.getAvgRating()*customerVO.getRatings().size()-1)+driverRating)/customerVO.getRatings().size());
		CabsDao.saveCustomer(customerVO);
	}

	private void addDriverRating(DriverVO driverVO, long cid, double customerRating){
		List<RatingHistoryVO> driverRatingList = driverVO.getRatings();
		if(driverRatingList==null){
			driverRatingList = new ArrayList<RatingHistoryVO>();
			driverRatingList.add(new RatingHistoryVO(cid, customerRating));
			driverVO.setRatings(driverRatingList);
			driverVO.setAvgRating(((driverVO.getAvgRating()*driverVO.getRatings().size()-1)+customerRating)/driverVO.getRatings().size());
			CabsDao.saveDriver(driverVO);
			return;
		}
		
		int added = 0;
		for(int i=0;i<driverRatingList.size();i++){
			RatingHistoryVO ratingVO = driverRatingList.get(0);
			if(ratingVO.getId()==cid){
				driverRatingList.remove(i);
				driverRatingList.add(new RatingHistoryVO(cid, customerRating));
				added = 1;
			}
			
		}
		if(added==0)
			driverRatingList.add(new RatingHistoryVO(cid, customerRating));
		//driverVO.setAvgRating(((driverVO.getAvgRating()*driverVO.getRatings().size())+customerRating)/driverVO.getRatings().size()+1);
		driverVO.setRatings(driverRatingList);
		driverVO.setAvgRating(((driverVO.getAvgRating()*driverVO.getRatings().size()-1)+customerRating)/driverVO.getRatings().size());
		CabsDao.saveDriver(driverVO);
	}

}
