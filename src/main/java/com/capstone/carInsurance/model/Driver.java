package com.capstone.carInsurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DRIVER_DETAILS")
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DRIVER_ID")
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
	
	private double quotation;
	

}
