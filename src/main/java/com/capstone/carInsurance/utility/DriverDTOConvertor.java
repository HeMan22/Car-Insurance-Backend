package com.capstone.carInsurance.utility;

import com.capstone.carInsurance.dto.DriverDTO;
import com.capstone.carInsurance.model.Driver;

public class DriverDTOConvertor {

	public static Driver driverDtoToEntity(DriverDTO driverDTO) {
		
		Driver driverObj = new Driver();
		
		
		driverObj.setPrefix(driverDTO.getPrefix());
		driverObj.setFirstName(driverDTO.getFirstName());
		driverObj.setLastName(driverDTO.getLastName());
		driverObj.setContactNo(driverDTO.getContactNo());
		driverObj.setEmail(driverDTO.getEmail());
		
		driverObj.setAddressLine1(driverDTO.getAddressLine1());
		driverObj.setAddressLine2(driverDTO.getAddressLine2());
		driverObj.setCity(driverDTO.getCity());
		driverObj.setPostCode(driverDTO.getPostCode());
		
		driverObj.setVehicleType(driverDTO.getVehicleType());
		driverObj.setEngineSize(driverDTO.getEngineSize());
		driverObj.setAdditionalDriver(driverDTO.getAdditionalDriver());
		driverObj.setCurrentValue(driverDTO.getCurrentValue());
		driverObj.setOutsideState(driverDTO.getOutsideState());
		driverObj.setCommercialUse(driverDTO.getCommercialUse());
		driverObj.setRegisteredDate(driverDTO.getRegisteredDate());
		
		return driverObj;
	}
}
