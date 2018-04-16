package com.booking.cabs.service;

import java.util.List;

import com.booking.cabs.vo.CustomerVO;

public interface CustomerService {
	public CustomerVO addCustomer(long id);
	public CustomerVO getCustomer(long id);
	public double getAvgRating(long id);
	public List<CustomerVO> getTopRatedCustomersList(int numberOfCustomers, double rating);
}
