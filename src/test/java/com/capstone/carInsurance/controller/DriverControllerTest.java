package com.capstone.carInsurance.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.capstone.carInsurance.controllers.DriverServiceController;
import com.capstone.carInsurance.model.Driver;
import com.capstone.carInsurance.services.DriverService;
import com.capstone.carInsurance.utility.DriverDTOConvertor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DriverServiceController.class)
class DriverControllerTest {

	private static final String SAVE_ENDPOINT = "/api/v1/carInsurance/driver";
	private static final String GET_ENDPOINT = "/api/v1/carInsurance/driver/{id}";
	private static final String GETALL_ENDPOINT = "";
	private static final String DELETE_ENDPOINT = "";

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private DriverService service;

	@MockBean
	private DriverDTOConvertor driverDTOConvertor;

//	@Autowired
//	private DriverRepository repository;

	private Driver driverOne;
	private Driver driverInvalid;
	private String EMAILONE = "heman.tri2208@gmail.com";

	@BeforeEach
	void setup() {
		driverOne = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC", "NishatGanj",
				"Lucknow", "2260060", "Cabriolet", "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);

		driverInvalid = new Driver((long) 1, "Mr.", "Himanshu", "Tripathi", "9458706580", EMAILONE, "PMC", "NishatGanj",
				"Lucknow", "2260060", null, "1600", 25000, 2, "Yes", "Yes", "7/31/2021", 324.21);

//		repository.save(driverOne);
	}

	@Test
	void whenValidInputThenReturn201Test() throws JsonProcessingException, Exception {
		MvcResult mvcResult = mvc
				.perform(post(SAVE_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(driverOne)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andDo(MockMvcResultHandlers.print()).andReturn();

		System.out.println("Result 1--> " + mvcResult.getResponse().getContentAsString());

		assertEquals(true, mvcResult.getResponse().getContentAsString().contains("Driver Saved"));
	}

	@Test
	void whenInvalidInputThenReturn400AndErrorResult() throws JsonProcessingException, Exception {
		MvcResult mvcResult = mvc
				.perform(post(SAVE_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(driverInvalid)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andDo(MockMvcResultHandlers.print())
				.andReturn();

		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		System.out.println("Result 2--> " + actualResponseBody);

		assertEquals(true,
				actualResponseBody.contains("[vehicleType : must not be null,vehicleType : must not be empty]"));
	}

}
