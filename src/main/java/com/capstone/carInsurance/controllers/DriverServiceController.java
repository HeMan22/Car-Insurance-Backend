package com.capstone.carInsurance.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.carInsurance.services.DriverService;

@RestController
@RequestMapping("/api/v1/carInsurance")
public class DriverServiceController {

	DriverService driverService;
	
	public DriverServiceController(DriverService driverService) {
		super();
		this.driverService=driverService;
	}
	
}
