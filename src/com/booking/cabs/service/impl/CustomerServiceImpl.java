package com.booking.cabs.service.impl;

import java.util.Date;
import java.util.List;

import com.booking.cabs.dao.CabsDao;
import com.booking.cabs.service.CustomerService;
import com.booking.cabs.vo.CustomerVO;
import com.booking.cabs.vo.RatingHistoryVO;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public CustomerVO addCustomer(long id) {
		if(id == 0)
			id = new Date().getTime();
		CustomerVO customerVO = new CustomerVO(id, 0.0, null);
		CabsDao.saveCustomer(customerVO);
		return customerVO;
	}

	@Override
	public CustomerVO getCustomer(long id) {
		return CabsDao.getCustomer(id);
	}

	@Override
	public double getAvgRating(long id) {
		double avgRating = 0;
		CustomerVO customerVO = getCustomer(id);
		if(customerVO==null)
			return avgRating;
		List<RatingHistoryVO> ratingList = customerVO.getRatings();
		if(ratingList == null)
			return avgRating;
		double sum = 0;
		for(RatingHistoryVO ratingHistoryVO : ratingList){
			sum+=ratingHistoryVO.getRating();
		}
		avgRating = sum/ratingList.size();
		customerVO.setAvgRating(avgRating);
		CabsDao.saveCustomer(customerVO);
		return avgRating;
	}
	
	public List<CustomerVO> getTopRatedCustomersList(int numberOfCustomers, double rating){
		List<CustomerVO> customerList = CabsDao.getAllCustomers();
		if(customerList.isEmpty())
			return null;
		if(Double.compare(rating, 0)==0){
			if(numberOfCustomers==0||numberOfCustomers>=customerList.size())
				return customerList;
			return customerList.subList(0, numberOfCustomers);
		}
		for(int i =0 ;i<customerList.size();i++){
			if(Double.compare(customerList.get(i).getAvgRating(),rating)<0)
				customerList.remove(i);
		}
		
		if(numberOfCustomers==0||numberOfCustomers>=customerList.size())
			return customerList;
		return customerList.subList(0, numberOfCustomers);
	}

}
