package com.capstone.carInsurance.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CarInsuranceFactorTest {

	String vehicleType;

	@InjectMocks
	VehicleFactor vehicleFactor;

//	CarInsuranceFactorTest(VehicleFactor vehicleFactor){
//		this.vehicleFactor = vehicleFactor;		
//	}

	@BeforeEach
	void setUp() {
		vehicleType = "Hatchback";
	}

	@Test
	public void givenVehicleTypeReturnCorrectVehicleTypeFactor() {
		double vehicleTypeFactor = vehicleFactor.getVehicleTypeFactor(vehicleType);

		double factor = 0.0;
		switch (vehicleType) {
		case "Cabriolet":
			factor = 1.3;
			break;

		case "Coupe":
			factor = 1.4;
			break;
			
		case "Estate":
			factor = 1.5;
			break;
			
		case "Hatchback":
			factor = 1.6;
			break;
			
		case "Others":
			factor = 1.7;
			break;
		}
		System.out.println(vehicleType + " : " + factor + " --> " + vehicleTypeFactor);
		assertEquals(factor, vehicleTypeFactor);
	}
}
