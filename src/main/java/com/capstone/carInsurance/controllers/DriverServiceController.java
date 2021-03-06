package com.capstone.carInsurance.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.carInsurance.dto.DriverDTO;
import com.capstone.carInsurance.exceptions.DriverNotFoundException;
import com.capstone.carInsurance.model.Driver;
import com.capstone.carInsurance.services.DriverService;
import com.capstone.carInsurance.utility.APIResponse;
import com.capstone.carInsurance.utility.DriverDTOConvertor;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@Log4j2
@RestController
@RequestMapping("/api/v1/carInsurance")
public class DriverServiceController {

	private static final String SUCCESS = "SUCCESS";

	DriverDTOConvertor driverDTOConvertor;
	DriverService driverService;

	@Autowired
	public DriverServiceController(DriverService driverService, DriverDTOConvertor driverDTOConvertor) {
		this.driverService = driverService;
		this.driverDTOConvertor = driverDTOConvertor;
	}

	@GetMapping("/ping")
	public ResponseEntity<APIResponse> getConnectionTest() {
		APIResponse connectionTest = new APIResponse(SUCCESS, "CAR Insurance API is Running", null);
		return new ResponseEntity<>(connectionTest, HttpStatus.OK);
	}

	@PostMapping("/driver")
	public ResponseEntity<APIResponse> saveDriverInfo(@Valid @RequestBody DriverDTO driverDTO) {

		// Converting DTO to Driver Entity
		Driver newDriverObj = driverDTOConvertor.driverDtoToEntity(driverDTO);

		// Calling DriverServices to save the Driver Info
		Driver saveDriverInfo = driverService.saveDriver(newDriverObj);

		// Calculating Quote
		double insuranceQuote = driverService.getInsuranceQuote(saveDriverInfo);

		log.info("After Saving the User Calculated Insurance Quote : " + insuranceQuote);

		// Converting Driver Entity to DTO before Sending as Response
		DriverDTO saveDriverDTOInfo = driverDTOConvertor.entityToDriverDTO(saveDriverInfo);

		// Setting API Response
		APIResponse saveDriverResponse = new APIResponse(SUCCESS, "Driver Saved", saveDriverDTOInfo);

		return new ResponseEntity<>(saveDriverResponse, HttpStatus.CREATED);
	}

	@GetMapping("/driver/quote/{id}")
	public ResponseEntity<APIResponse> getDriverCarInsuranceQuote(@PathVariable long id)
			throws DriverNotFoundException {

		// First Fetching the Driver to check whether any Driver with given ID exists or
		// not
		Driver driverInfo = driverService.getDriver(id);

		// Calculating Car insurance Quote with the Driver Info retrieved
		double carInsuranceQuote = driverService.getInsuranceQuote(driverInfo);

		log.info("Driver with ID : " + id + " Insurance Quote Calculated : " + carInsuranceQuote);
		// Setting the API Response

		APIResponse getCarInsuranceQuote = new APIResponse(SUCCESS, "Driver Car Insurance Quote Calculated",
				carInsuranceQuote);

		return new ResponseEntity<>(getCarInsuranceQuote, HttpStatus.OK);
	}

	@GetMapping("/driver/{id}")
	public ResponseEntity<APIResponse> getDriverByDriverID(@PathVariable Long id) throws DriverNotFoundException {

		// Getting Driver Info with the ID provided
		Driver getDriverObj = driverService.getDriver(id);

		APIResponse getDriverResponse = new APIResponse(SUCCESS, "Driver Fetched", getDriverObj);

		return new ResponseEntity<>(getDriverResponse, HttpStatus.OK);

	}

	@GetMapping("/driver/all")
	public ResponseEntity<APIResponse> getAllDriverList() {
		List<Driver> getAllDriver = driverService.getAllDriverList();

		APIResponse getAllDriverResponse = new APIResponse(SUCCESS, "Driver List Fetched", getAllDriver);

		return new ResponseEntity<>(getAllDriverResponse, HttpStatus.OK);
	}

	@PutMapping("/driver")
	@ResponseBody
	public ResponseEntity<APIResponse> updateDriverDetails(@RequestBody DriverDTO driverDTO)
			throws DriverNotFoundException {

		log.info("Driver's ID whose Info needs to be Updated :  " + driverDTO.getDriverID());

		// Converting from DTO to Driver entity
		Driver driverinfoObj = driverDTOConvertor.driverDtoToEntity(driverDTO);

		// Calling DriverServices to update the Driver Details
		Driver updatedDriverInfo = driverService.updateDriverDetails(driverinfoObj);

		// Converting Driver entity to DTO before sending as Response
		DriverDTO updatedDriverDTOInfo = driverDTOConvertor.entityToDriverDTO(updatedDriverInfo);

		// Setting up API Response Body
		APIResponse updatedDriverAPIResponse = new APIResponse(SUCCESS, "Driver Details updated Successfully",
				updatedDriverDTOInfo);

		return new ResponseEntity<>(updatedDriverAPIResponse, HttpStatus.OK);
	}

	@DeleteMapping("/driver/{id}")
	public ResponseEntity<APIResponse> deleteDriverByDriverID(@PathVariable long id) throws DriverNotFoundException {

		// First Fetching the Driver to check whether any Driver with given ID exists or
		// not
		Driver driverObj = driverService.getDriver(id);

		String deleteResponse = driverService.deleteDriver(id);

		log.info(deleteResponse + "ID: " + id);

		// Converting Driver Entity to Driver DTO before sending in Response Body
		DriverDTO driverDTOObj = driverDTOConvertor.entityToDriverDTO(driverObj);

		APIResponse deleteDriverResponse = new APIResponse(SUCCESS, deleteResponse, driverDTOObj);

		return new ResponseEntity<>(deleteDriverResponse, HttpStatus.OK);

	}

}
