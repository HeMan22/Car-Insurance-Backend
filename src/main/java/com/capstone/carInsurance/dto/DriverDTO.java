package com.capstone.carInsurance.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

	private Long driverID;
	private String prefix;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String contactNo;

	@NotNull
	@NotEmpty
	private String email;

	private String addressLine1;
	private String addressLine2;
	private String city;
	private String postCode;

	@NotNull
	@NotEmpty
	private String vehicleType;
	@NotNull
	@NotEmpty
	private String engineSize;
	@NotNull
	@NotEmpty
	private Long currentValue;
	@NotNull
	@NotEmpty
	private int additionalDriver;
	@NotNull
	@NotEmpty
	private String commercialUse;
	@NotNull
	@NotEmpty
	private String outsideState;

	private String registeredDate;
}
