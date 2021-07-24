package com.capstone.carInsurance.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public Driver getDriver(Long driverId) throws DriverNotFoundException {

		Optional<Driver> driverInfo = driverRepository.findById(driverId);

		if (!driverInfo.isPresent()) {
			throw new DriverNotFoundException("No Driver with Driver ID: " + driverId + " can be found in the system.");
		}

		return driverInfo.get();
	}

	@Override
	public List<Driver> getAllDriverList() {

		List<Driver> driverInfo = driverRepository.findAll();

		return driverInfo.stream().collect(Collectors.toList());

	}

	@Override
	public String deleteDriver(Long driverId) throws DriverNotFoundException {

		Optional<Driver> driverInfo = driverRepository.findById(driverId);

		if (driverInfo.isEmpty()) {
			throw new DriverNotFoundException("No Driver with Driver ID: " + driverId + " can be found in the system.");
		}

		driverRepository.deleteById(driverId);

		return "Driver Info Deleted from the System";

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

	@Override
	public Driver updateDriverDetails(Driver driver) throws DriverNotFoundException {
		Driver driverObj = getDriver(driver.getDriverID());
		
		if(driverObj !=null) {
			//Save the new updates
			driverObj.setFirstName(driver.getFirstName());
			driverObj.setLastName(driver.getLastName());
			driverObj.setEmail(driver.getEmail());
			driverObj.setContactNo(driver.getContactNo());
			driverObj.setAddressLine1(driver.getAddressLine1());
			driverObj.setAddressLine2(driver.getAddressLine2());
			driverObj.setCity(driver.getCity());
			driverObj.setPostCode(driver.getPostCode());
			
			driverRepository.save(driverObj);
		}
		
		return driverObj;
	}

}
