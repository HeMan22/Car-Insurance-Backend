package com.capstone.carInsurance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.carInsurance.dto.DriverDTO;
import com.capstone.carInsurance.services.DriverService;
import com.capstone.carInsurance.utility.APIResponse;

@RestController
@RequestMapping("/api/v1/carInsurance")
public class DriverServiceController {

	DriverService driverService;
	@Autowired
	public DriverServiceController(DriverService driverService) {
		this.driverService=driverService;
	}
	
	@GetMapping("/ping")
	public ResponseEntity<APIResponse> getConnectionTest(){
		APIResponse connectionTest = new APIResponse("SUCCESS","CAR Insurance API is Running",null);
		return new ResponseEntity<APIResponse>(connectionTest,HttpStatus.OK);
	}
	
	@PostMapping("/save/driver")
	public ResponseEntity<APIResponse> saveDriverInfo(@RequestBody DriverDTO driverDTO){
				
		APIResponse saveDriver = new APIResponse("SUCCESS", "Driver Saved",null);
		
		return new ResponseEntity<APIResponse>(saveDriver, HttpStatus.CREATED);
	}
}
