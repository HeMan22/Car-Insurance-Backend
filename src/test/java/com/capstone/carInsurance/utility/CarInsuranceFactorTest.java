package com.capstone.carInsurance.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CarInsuranceFactorTest {

	String vehicleType;
	String engineSize;
	Long valueFactor;
	int additionalDrivers;
	String commericalUse;
	String outsideStateUse;

	@InjectMocks
	VehicleFactor vehicleFactor;

//	@Autowired
//	public CarInsuranceFactorTest(VehicleFactor vehicleFactor){
//		super();
//		this.vehicleFactor = vehicleFactor;		
//	}

	@BeforeEach
	void setUp() {
		vehicleType = "HatchBack";
		engineSize = "Others";
		valueFactor = (long) 5000;
		additionalDrivers = 5;
		commericalUse = "No";
		outsideStateUse = "Yes";
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

		case "HatchBack":
			factor = 1.6;
			break;

		case "Others":
			factor = 1.7;
			break;
		}
		System.out.println(vehicleType + " : " + factor + " --> " + vehicleTypeFactor);
		assertEquals(factor, vehicleTypeFactor);
	}

	@Test
	public void givenEngineSizeReturnCorrectEngineSizeFactor() {
		double engineSizeFactor = vehicleFactor.getVehicleEngineSizeFactor(engineSize);
		double factor;

		switch (engineSize) {
		case "1000":
			factor = 1.0;
			break;
		case "1600":
			factor = 1.6;
			break;
		case "2000":
			factor = 2.0;
			break;
		case "2500":
			factor = 2.5;
			break;
		case "3000":
			factor = 3.0;
			break;
		case "Others":
			factor = 3.5;
			break;
		default:
			factor = 0.0;
			break;
		}

		System.out.println(engineSize + " : " + factor + " --> " + engineSizeFactor);
		assertEquals(factor, engineSizeFactor);

	}

	@Test
	public void givenVehicleValueReturnVehicleValueFactor() {

		double vehicleValueFactor = vehicleFactor.getVehicleValueFactor(valueFactor);
		double factor = (valueFactor <= 5000) ? 1.0 : 1.2;

		System.out.println("Expected : " + factor + " Actual : " + vehicleValueFactor);
		assertEquals(factor, vehicleValueFactor);

	}

	@Test
	public void givenAddiitonalDriversReturnAdditionalDriversFactor() {

		double additionalDriversFactor = vehicleFactor.getVehicleAdditionalDriverFactor(additionalDrivers);
		double factor = (additionalDrivers <= 2) ? 1.1 : 1.2;

		System.out.println("Additional Drivers " + additionalDrivers + "--> Expected : " + factor + " Actual : "
				+ additionalDriversFactor);
		assertEquals(factor, additionalDriversFactor);

	}

	@Test
	public void givenCommercialUseReturnCommercialUseFactor() {

		double commericalUseFactor = vehicleFactor.getVehicleCommericalUseFactor(commericalUse);
		double factor = (commericalUse.equals("Yes")) ? 1.1 : 1.0;

		System.out.println(
				"commericalUse " + commericalUse + "--> Expected : " + factor + " Actual : " + commericalUseFactor);
		assertEquals(factor, commericalUseFactor);

	}
	
	@Test
	public void givenOutsideStateUseReturnOutsideStateUseFactor() {
		
		double outsideStateUseFactor = vehicleFactor.getVehicleOutsideStateUseFactor(outsideStateUse);
		double factor = (outsideStateUse.equals("Yes")?1.1:1.0);
		
		System.out.println(
				"outsideStateUse " + outsideStateUse + "--> Expected : " + factor + " Actual : " + outsideStateUseFactor);
		assertEquals(factor, outsideStateUseFactor);
	}

}
