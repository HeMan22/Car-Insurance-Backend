package com.capstone.carInsurance.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;

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
import com.capstone.carInsurance.exceptions.DriverNotFoundException;
import com.capstone.carInsurance.model.Driver;
import com.capstone.carInsurance.services.DriverService;
import com.capstone.carInsurance.utility.DriverDTOConvertor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DriverServiceController.class)
class DriverControllerTest {

	private static final String SAVE_ENDPOINT = "/api/v1/carInsurance/driver";
	private static final String GET_ENDPOINT = "/api/v1/carInsurance/driver/1";
	private static final String GETALL_ENDPOINT = "/api/v1/carInsurance/driver/all";
	private static final String DELETE_ENDPOINT = "/api/v1/carInsurance/driver/1";

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

		assertEquals(true, actualResponseBody.contains("must not be null"));

	}

	@Test
	void whenUpdateThenReturnSuccess() throws Exception {
		Driver driver = new Driver();
		driver.setFirstName("Goku");

		MvcResult mvcResult = mvc.perform(put(SAVE_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(driver))).andReturn();

		int status = mvcResult.getResponse().getStatus();
		System.out.println("Status : " + status);
		assertEquals(200, status);
		System.out.println("Update : " + mvcResult.getResponse().getContentAsString());

	}

	@Test
	void whenDriverThenGetAllDriverListTest() throws Exception {

		when(service.getAllDriverList()).thenReturn(List.of(driverOne));

		MvcResult mvcResult = mvc.perform(get(GETALL_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = mvcResult.getResponse().getStatus();

		String content = mvcResult.getResponse().getContentAsString();

		System.out.println(status + " : " + content + " -> " + mapper.readValue(content, Driver.class));

		assertEquals(true, content.contains("Driver List Fetched"));

	}

	@Test
	void whenDriverIDThenGetDriverDetailsTest() throws Exception {

		when(service.getDriver(anyLong())).thenReturn(driverOne);

		MvcResult mvcResult = mvc.perform(get(GET_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(mvcResult.getResponse().getStatus() + " : " + content);

		assertEquals(true, content.contains("Driver Fetched"));
	}

	@Test
	void whenDriverIDThenDeleteDriverDetailsTest() throws DriverNotFoundException, Exception {
		when(service.deleteDriver(anyLong())).thenReturn("Driver Info Deleted from the System");

		MvcResult mvcResult = mvc.perform(delete(DELETE_ENDPOINT).accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(mvcResult.getResponse().getStatus() + " : " + content);
		assertEquals(true, content.contains("Driver Info Deleted"));
	}

}
