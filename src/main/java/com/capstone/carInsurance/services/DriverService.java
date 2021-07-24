package com.capstone.carInsurance.services;

import java.util.List;

import com.capstone.carInsurance.exceptions.DriverNotFoundException;
import com.capstone.carInsurance.model.Driver;

public interface DriverService {
public Driver saveDriver(Driver driver);
public Driver getDriver(Long driverId) throws DriverNotFoundException;
public String deleteDriver(Long driverId) throws DriverNotFoundException;
public double getInsuranceQuote(Driver driver);
public List<Driver> getAllDriverList();
public Driver updateDriverDetails(Driver driver) throws DriverNotFoundException;
}
