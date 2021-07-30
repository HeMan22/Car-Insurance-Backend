package com.capstone.carInsurance.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CarInsuranceQuoteTestAmount {

	VehicleFactor vehicleFactor = new VehicleFactor();;

//	private Map<String,Object> vehicleFactor1 = new HashMap<>();
//	private Map<String,Object> vehicleFactor2 = new HashMap<>();

//	@BeforeEach
//	void setUp() {
//		vehicleFactor1.put("vehicleType", "HatchBack");
//		vehicleFactor1.put("engineSize", "1600");
//		vehicleFactor1.put("valueFactor", (long)5000);
//		vehicleFactor1.put("additionalDrivers", 3);
//		vehicleFactor1.put("commericalUse", "Yes");
//		vehicleFactor1.put("outsideStateUse", "Yes");
//		
//		vehicleFactor2.put("vehicleType", "Cabriolet");
//		vehicleFactor2.put("engineSize", "3000");
//		vehicleFactor2.put("valueFactor", (long)15000);
//		vehicleFactor2.put("additionalDrivers", 1);
//		vehicleFactor2.put("commericalUse", "No");
//		vehicleFactor2.put("outsideStateUse", "No");
//	}

	@Test
	void givenVehicleProperty1ReturnCorrectAmountTest() {

		double vehicleType = vehicleFactor.getVehicleTypeFactor("HatchBack");
		double engineSize = vehicleFactor.getVehicleEngineSizeFactor("1600");
		double valueFactor = vehicleFactor.getVehicleValueFactor((long) 5000);
		double additionalDrivers = vehicleFactor.getVehicleAdditionalDriverFactor(3);
		double commercialUse = vehicleFactor.getVehicleOutsideStateUseFactor("Yes");
		double outsideStateUse = vehicleFactor.getVehicleOutsideStateUseFactor("Yes");

		System.out.println(commercialUse);
		System.out.println("Quote --> " + vehicleType + " " + engineSize + " -->" + valueFactor + " "
				+ additionalDrivers + " " + outsideStateUse + " ");
		
		
//		System.out.println(vehicleFactor.commercialUseFactor);	
//		double quote = vehicleFactor.getVehicleInsuranceQuotation();
//		System.out.println("Quote --> "+quote);
		
		
		double quote = 100 * vehicleType * engineSize * valueFactor * additionalDrivers * commercialUse
				* outsideStateUse;
		double precisedQuote = Double.parseDouble(String.format("%.3f", quote));
		assertEquals(371.712, precisedQuote);
	}

}
