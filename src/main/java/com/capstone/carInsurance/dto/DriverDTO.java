package com.capstone.carInsurance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

	private Long driverID;
	private String prefix;
	private String firstName;
	private String lastName;
	private String contactNo;
	private String email;
	
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postCode;
	
	private String vehicleType;
	private String engineSize;
	private Long currentValue;
	private int additionalDriver;
	private String commercialUse;
	private String outsideState;
	private String registeredDate;
}
