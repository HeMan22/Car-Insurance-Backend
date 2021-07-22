package com.capstone.carInsurance.utility;

import java.util.HashMap;

public class VehicleFactor {

	public double getVehicleTypeFactor(String vehicleType) {

		HashMap<String, Double> vehicleTypeFactor = new HashMap<>();

		vehicleTypeFactor.put("Cabriolet", 1.3);
		vehicleTypeFactor.put("Coupe", 1.4);
		vehicleTypeFactor.put("Estate", 1.5);
		vehicleTypeFactor.put("Hatchback", 1.6);
		vehicleTypeFactor.put("Others", 1.7);

		System.out.println("Factor Class "+vehicleType+" : "+ vehicleTypeFactor.get(vehicleType));
		return vehicleTypeFactor.get(vehicleType);
	}
	
	public double getVehicleEngineFactor(String engineSize) {
		
		HashMap<String,Double> engineSizeFactor = new HashMap<>();
		
		engineSizeFactor.put("1000", 1.0);
		engineSizeFactor.put("1600", 1.6);
		engineSizeFactor.put("2000", 2.0);
		engineSizeFactor.put("2500", 2.5);
		engineSizeFactor.put("3000", 3.0);
		engineSizeFactor.put("Other", 3.5);
		
		return engineSizeFactor.get(engineSize);
	}

}
