package com.capstone.carInsurance.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

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
	@NotBlank
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
	@Range(min=1, max=50000)
	private long currentValue;
	@NotNull
	@Min(1)@Max(4)
	private int additionalDriver;
	@NotNull
	@NotBlank
	private String commercialUse;
	@NotNull
	@NotBlank
	private String outsideState;

	private String registeredDate;
}
