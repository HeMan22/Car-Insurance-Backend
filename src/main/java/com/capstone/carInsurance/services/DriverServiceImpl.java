package com.capstone.carInsurance.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capstone.carInsurance.exceptions.DriverNotFoundException;
import com.capstone.carInsurance.model.Driver;
import com.capstone.carInsurance.repository.DriverRepository;
import com.capstone.carInsurance.utility.VehicleFactor;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

	private DriverRepository driverRepository;
	private VehicleFactor vehicleFactor;

	@Autowired
	public DriverServiceImpl(DriverRepository driverRepository, VehicleFactor vehicleFactor) {
		super();
		this.driverRepository = driverRepository;
		this.vehicleFactor = vehicleFactor;
		
	}

	@Override
	public Driver saveDriver(Driver driver) {
		return driverRepository.save(driver);
	}

	@Override
	public Driver getDriver(Long driverId) throws DriverNotFoundException{

		Optional<Driver> driverInfo = driverRepository.findById(driverId);
		
		if(!driverInfo.isPresent()) {
			throw new DriverNotFoundException("No Driver with Driver ID: "+ driverId+" can be found in the system.");
		}
		
		return driverInfo.get();
	}

	@Override
	public void deleteDriver(Long driverId) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public double getInsuranceQuote(Driver driver) {
		vehicleFactor.getVehicleTypeFactor(driver.getVehicleType());
		vehicleFactor.getVehicleEngineSizeFactor(driver.getEngineSize());
		vehicleFactor.getVehicleAdditionalDriverFactor(driver.getAdditionalDriver());
		vehicleFactor.getVehicleCommericalUseFactor(driver.getCommercialUse());
		vehicleFactor.getVehicleOutsideStateUseFactor(driver.getOutsideState());
		vehicleFactor.getVehicleValueFactor(driver.getCurrentValue());
		
		
		return vehicleFactor.getVehicleInsuranceQuotation();
		
	}

}
