package com.capstone.carInsurance.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capstone.carInsurance.model.Driver;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DriverRepositoryTest {

	@Autowired
	private DriverRepository driverRepository;

	private Driver driverOne;

	@BeforeEach
	void setup() {
		driverOne = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", "heman.tri2208@gmail.com", "PMC",
				"NishatGanj", "Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);
		driverRepository.save(driverOne);
	}

	@AfterEach
	void tearDown() {
		driverOne = null;
	}

	@Test
	void givenDriverIDWhenDriverExistsThenReturnTrue() {

		boolean exists = driverRepository.existsById((long) 2);
		System.out.println("First Test" + driverRepository.findAll());
		assertTrue(exists);
	}

	@Test
	void givenDriverIDWhenDriverDoesNotExistsThenReturnFalse() {
		boolean exists = driverRepository.existsById((long) 0);
		System.out.println("Second Test" + driverRepository.findAll());
		assertFalse(exists);
	}

	@Test
	void givenDriverIDWhenDriverExistsThenReturnOptionalWithDriver() {

		Optional<Driver> optionalDriver = driverRepository.findById((long) 1);
		System.out.println("Third Test" + driverRepository.findAll());
		assertTrue(optionalDriver.isPresent());

		Driver driver = optionalDriver.get();
		assertEquals("Himanshu", driver.getFirstName());

	}
}
