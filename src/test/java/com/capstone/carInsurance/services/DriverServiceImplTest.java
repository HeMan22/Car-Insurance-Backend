package com.capstone.carInsurance.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyDouble;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.carInsurance.exceptions.DriverNotFoundException;
import com.capstone.carInsurance.model.Driver;
import com.capstone.carInsurance.repository.DriverRepository;
import com.capstone.carInsurance.utility.VehicleFactor;

//@ExtendWith(MockitoExtension.class)
////@AutoConfigureTestDatabase(replace = Replace.NONE)
//class DriverServiceImplTest {
//
//	private static final String EMAILONE = "heman.tri2208@gmail.com";
//	
//	@InjectMocks
//	private DriverServiceImpl service;
//	@Mock
//	private DriverRepository repository;
//	@Mock
//	private VehicleFactor vehicleFactor;
//	
//	private Driver driverOne;
//	
//	@BeforeEach
//	void setup() {
//		driverOne = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC",
//				"NishatGanj", "Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);
//		repository.save(driverOne);
//	}
//	
//	@Test
//	void whenDriverSaveThenSetQuotationTest() {
//		
//		// Configure behaviour of Mock object for this test
//		when(repository.save(any(Driver.class))).thenReturn(driverOne);
//		
////		double quote = service.getInsuranceQuote(driverOne);
//		
//		//Actual Call to Service
//		 Driver driver = service.saveDriver(driverOne);
//		 System.out.println(driver.getQuotation());
//		 //Verification of Result from Service
//		 assertAll( ()->{
//				 assertNotNull((Double)driver.getQuotation());
//				 assertEquals(EMAILONE, driver.getEmail());
//				 assertNotNull(driver);
//		 });
//		 
//		System.out.println(driver);
//		 verify(repository, atLeastOnce()).save(any(Driver.class));
//		 verifyNoMoreInteractions(repository);
//		
//	}
//	@Test
//	void whenDriverExistsThenReturnDriverTest() throws DriverNotFoundException {
//		when(repository.existsById(anyLong())).thenReturn(true);
//		System.out.println(service.getAllDriverList());
//		System.out.println(driverOne);
//
////		Driver driver = service.getDriver(driverOne.getDriverID());
//		
//		
//		Optional<Driver> driver = Optional.of(service.getDriver((long)1));
//		
//		assertAll(()-> {
//			verify(repository, times(1)).existsById(anyLong());
//			verifyNoMoreInteractions(repository);
//		});
//	}
//	
//}
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DriverServiceImplTest {

	private static final String EMAILONE = "heman.tri2208@gmail.com";

	@Autowired
	private DriverServiceImpl service;
	@MockBean
	private DriverRepository repository;
	@MockBean
	private VehicleFactor factor;

	@Test
	void testGetDriver() throws DriverNotFoundException {
		Driver driverOne = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC",
				"NishatGanj", "Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);
		repository.save(driverOne);
//		System.out.println(repository.findAll());

		doReturn(Optional.of(driverOne)).when(repository).findById((long) 1);

		Driver driver = service.getDriver((long) 1);
		System.out.println("1 driver --> " + driver);
		org.junit.jupiter.api.Assertions.assertSame(driverOne, driver);
	}

//	@Test
//	void testGetDriverIDNotPresent() throws DriverNotFoundException {
//		//doThrow(new DriverNotFoundException("No Driver with Driver ID: " + anyLong() + " can be found in the system.")).when(repository).findById(anyLong());
//		
//		when(repository.findById(anyLong())).thenThrow(new DriverNotFoundException("No Driver with Driver ID: " + 1 + " can be found in the system."));
//		
//		Driver driver = service.getDriver(anyLong());
//		
//
//		System.out.println("Error -> " + driver);
//	}

	@Test
	void testGetAllDriver() {
		Driver driverOne = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC",
				"NishatGanj", "Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);
		Driver driverTwo = new Driver((long) 2, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC",
				"NishatGanj", "Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);

		List<Driver> driverList = new ArrayList<>();
		driverList.add(driverTwo);
		driverList.add(driverOne);

		System.out.println(driverList);
		doReturn(driverList).when(repository).findAll();

		List<Driver> driver = service.getAllDriverList();
		System.out.println(" --> " + driver);
		assertIterableEquals(driverList, driver);

	}

	@Test
	void testDeleteDriver() throws DriverNotFoundException {
		String delete = "Driver Info Deleted from the System";

		Driver driverOne = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC",
				"NishatGanj", "Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);
		repository.save(driverOne);

		// set up mocked result on Delete
		doNothing().when(repository).deleteById(anyLong());

		// set up mocked result for findById() as service.DeleteDriver() calls
		// findById()
		when(repository.findById(anyLong())).thenReturn(Optional.of(driverOne));

		// call the method we wish to test
		String result = service.deleteDriver(anyLong());

		assertEquals(delete, result);

		// verify the method was called
		verify(repository).deleteById(anyLong());
	}

	@Test
	void testGetQuotation() {
		Driver driverOne = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC",
				"NishatGanj", "Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);

		when(factor.getVehicleInsuranceQuotation()).thenReturn((double) 324.21);
//		when(factor.getVehicleTypeFactor("Cabriolet")).thenReturn(anyDouble());
//		when(factor.getVehicleEngineSizeFactor("1600")).thenReturn(anyDouble());
//		when(factor.getVehicleAdditionalDriverFactor(2)).thenReturn(anyDouble());
//		when(factor.getVehicleCommericalUseFactor("Yes")).thenReturn(anyDouble());
//		when(factor.getVehicleOutsideStateUseFactor("Yes")).thenReturn(anyDouble());
//		when(factor.getVehicleValueFactor((long)5000)).thenReturn(anyDouble());

		double quote = service.getInsuranceQuote(driverOne);
		System.out.println("Vehicle Quote -> " + quote);
		assertEquals(driverOne.getQuotation(), quote);
	}
	
	@Test
	void testSaveDriver() {
		Driver driverOne = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC",
				"NishatGanj", "Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);
		
		when(repository.save(driverOne)).thenReturn(driverOne);
		
		Driver driver = service.saveDriver(driverOne);
		
		assertSame(driverOne, driver);
		
	}

}