package com.capstone.carInsurance.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capstone.carInsurance.dto.DriverDTO;
import com.capstone.carInsurance.model.Driver;

@Component
public class DriverDTOConvertor {

	@Autowired
	private ModelMapper modelMapper;

	public Driver driverDtoToEntity(DriverDTO driverDTO) {

//		Driver driverObj = new Driver();
//		driverObj.setDriverID(driverDTO.getDriverID());
//		driverObj.setPrefix(driverDTO.getPrefix());
//		driverObj.setFirstName(driverDTO.getFirstName());
//		driverObj.setLastName(driverDTO.getLastName());
//		driverObj.setContactNo(driverDTO.getContactNo());
//		driverObj.setEmail(driverDTO.getEmail());
//		
//		driverObj.setAddressLine1(driverDTO.getAddressLine1());
//		driverObj.setAddressLine2(driverDTO.getAddressLine2());
//		driverObj.setCity(driverDTO.getCity());
//		driverObj.setPostCode(driverDTO.getPostCode());
//		
//		driverObj.setVehicleType(driverDTO.getVehicleType());
//		driverObj.setEngineSize(driverDTO.getEngineSize());
//		driverObj.setAdditionalDriver(driverDTO.getAdditionalDriver());
//		driverObj.setCurrentValue(driverDTO.getCurrentValue());
//		driverObj.setOutsideState(driverDTO.getOutsideState());
//		driverObj.setCommercialUse(driverDTO.getCommercialUse());
//		driverObj.setRegisteredDate(driverDTO.getRegisteredDate());

		return modelMapper.map(driverDTO, Driver.class);
	}
	
	public DriverDTO entityToDriverDTO(Driver driver) {
		
		return modelMapper.map(driver, DriverDTO.class);
	}
}
