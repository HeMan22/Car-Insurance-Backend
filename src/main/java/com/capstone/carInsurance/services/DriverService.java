package com.capstone.carInsurance.services;

import com.capstone.carInsurance.model.Driver;

public interface DriverService {
public Driver saveDriver(Driver driver);
public Driver getDriver(Long driverId);
public void deleteDriver(Long driverId);
}
