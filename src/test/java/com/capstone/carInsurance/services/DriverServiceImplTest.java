package com.capstone.carInsurance.services;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
		System.out.println(" --> "+driver);
		assertIterableEquals(driverList, driver);
		
	}

}