package com.capstone.carInsurance.utility;

import java.util.HashMap;

public class VehicleFactor {

	// Setting Factor for Vehicle type Case
	public double getVehicleTypeFactor(String vehicleType) {

		HashMap<String, Double> vehicleTypeFactor = new HashMap<>();

		vehicleTypeFactor.put("Cabriolet", 1.3);
		vehicleTypeFactor.put("Coupe", 1.4);
		vehicleTypeFactor.put("Estate", 1.5);
		vehicleTypeFactor.put("Hatchback", 1.6);
		vehicleTypeFactor.put("Other", 1.7);

		System.out.println("Factor Class " + vehicleType + " : " + vehicleTypeFactor.get(vehicleType));
		return vehicleTypeFactor.get(vehicleType);
	}

	// Setting Factor for Engine Size Case
	public double getVehicleEngineSizeFactor(String engineSize) {

		HashMap<String, Double> engineSizeFactor = new HashMap<>();

		engineSizeFactor.put("1000", 1.0);
		engineSizeFactor.put("1600", 1.6);
		engineSizeFactor.put("2000", 2.0);
		engineSizeFactor.put("2500", 2.5);
		engineSizeFactor.put("3000", 3.0);
		engineSizeFactor.put("Other", 3.5);

		return engineSizeFactor.get(engineSize);
	}

	// Setting Factor for Vehicle Value Case
	public double getVehicleValueFactor(Long vehicleValue) {

		double vehicleValueFactor = (vehicleValue <= 5000) ? 1.0 : 1.2;

		return vehicleValueFactor;
	}

	// Setting Factor for Additional Driver Case
	public double getVehicleAdditionalDriverFactor(int additionalDriver) {

		double vehicleAdditionalDriverFactor = (additionalDriver <= 2) ? 1.1 : 1.2;

		return vehicleAdditionalDriverFactor;
	}

	// Setting Factor for Commercial Use Case
	public double getVehicleCommericalUseFactor(String commercialUse) {

		double commercialUseFactor = (commercialUse.equals("Yes") ? 1.1 : 1.0);

		return commercialUseFactor;
	}

	// Setting Factor for Outside State Use Case
	public double getVehicleOutsideStateUseFactor(String outsideStateUse) {

		double outsideStateUseFactor = (outsideStateUse.equals("Yes") ? 1.1 : 1.0);
		
		return outsideStateUseFactor;
	}
	
}
