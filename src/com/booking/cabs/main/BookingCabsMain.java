package com.booking.cabs.main;

import com.booking.cabs.dao.CabsDao;
import com.booking.cabs.service.CustomerService;
import com.booking.cabs.service.DriverService;
import com.booking.cabs.service.TripService;
import com.booking.cabs.service.impl.CustomerServiceImpl;
import com.booking.cabs.service.impl.DriverServiceImpl;
import com.booking.cabs.service.impl.TripServiceImpl;

public class BookingCabsMain {

	public static void main(String[] args) {
		DriverService driverService = new DriverServiceImpl();
		CustomerService customerService = new CustomerServiceImpl();
		TripService tripService = new TripServiceImpl();
		//Drivers = 1,2 ,3
		//Customers = 1, 2, 3,
		driverService.addDriver(1);
		driverService.addDriver(2);
		driverService.addDriver(3);
		
		CabsDao.setDriverOnline(1);
		CabsDao.setDriverOnline(2);
		CabsDao.setDriverOnline(3);
		
		CabsDao.printDriverTable();
		
		customerService.addCustomer(1);
		customerService.addCustomer(2);
		customerService.addCustomer(3);
		
		CabsDao.printCustomerTable();
		
		CabsDao.printOnlineDrivers();

		//tripService.addTrip(cid, customerRating, driverId, driverRating);

		tripService.addTrip(1, 5, 1, 4);
		tripService.addTrip(2, 4, 1, 5);
		tripService.addTrip(3, 2, 1, 1);
		tripService.addTrip(1, 1, 2, 5);
		System.out.println("TOP CUSTOMERS  = "+customerService.getTopRatedCustomersList(0, 0));
		System.out.println("TOP DRIVERS = "+driverService.getTopRatedDriversList(0, 0));
		tripService.addTrip(2, 5, 2, 5);
		//tripService.addTrip(3, 5, 2, 4);
		tripService.addTrip(1, 2, 3, 3);
		tripService.addTrip(2, 5, 3, 4);
		tripService.addTrip(3, 3, 3, 3);
		
		System.out.println("ELIGIBLE DRIVERS FOR CUSTOMER 1 = "+tripService.getEligibleDriversList(1));
		
		System.out.println("ELIGIBLE DRIVERS FOR CUSTOMER 2 = "+tripService.getEligibleDriversList(2));
		
		CabsDao.setDriverOffline(3);
		
		System.out.println("ELIGIBLE DRIVERS FOR CUSTOMER 3 = "+tripService.getEligibleDriversList(3,true));
		
		
		System.out.println("TOP CUSTOMERS  = "+customerService.getTopRatedCustomersList(0, 0));
		System.out.println("TOP DRIVERS = "+driverService.getTopRatedDriversList(0, 0));
		
		
	}

}
