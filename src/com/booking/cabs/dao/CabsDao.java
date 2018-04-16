package com.booking.cabs.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.booking.cabs.vo.CustomerVO;
import com.booking.cabs.vo.DriverVO;

public class CabsDao {

	public static Map<Long, DriverVO> driverTable = new HashMap<Long, DriverVO>();
	public static Map<Long, CustomerVO> customerTable = new HashMap<Long, CustomerVO>();
	public static Set<Long> onlineDriverSet = new HashSet<Long>();

	public static CustomerVO getCustomer(long id){
		if(id !=0)
			return customerTable.get(id);
		return null;
	}

	public static DriverVO getDriver(long id){
		if(id !=0)
			return driverTable.get(id);
		return null;
	}

	public static void saveCustomer(CustomerVO customerVO){
		customerTable.put(customerVO.getCid(), customerVO);
	}

	public static void saveDriver(DriverVO driverVO){
		driverTable.put(driverVO.getId(), driverVO);
	}

	public static List<CustomerVO> getAllCustomers(){
		List<CustomerVO> customerList = new ArrayList<CustomerVO>();
		for(CustomerVO customerVO :customerTable.values()){
			customerList.add(customerVO);
		}
		return customerList;
	}

	public static List<DriverVO> getAllDrivers(){
		List<DriverVO> driverList = new ArrayList<DriverVO>();
		for(DriverVO driverVO :driverTable.values()){
			driverList.add(driverVO);
		}
		return driverList;
	}

	public static void setDriverOnline(long id){
		onlineDriverSet.add(id);
	}

	public static void setDriverOffline(long id){
		onlineDriverSet.remove(id);

	}

	public static boolean isDriverOnline(long id){
		return onlineDriverSet.contains(id);

	}

	public static void printDriverTable(){
		System.out.println("DRIVER TABLE = "+driverTable);
	}

	public static void printCustomerTable(){
		System.out.println("CUSTOMER TABLE = "+customerTable);
	}

	public static void printOnlineDrivers(){
		System.out.println("Online Drivers = "+onlineDriverSet);
	}
}
