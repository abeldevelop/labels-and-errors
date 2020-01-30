package com.abeldevelop.architecture.service.labelsanderrors.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CommonTestController {

	@Autowired
	protected MockMvc mockMvc;

	protected ObjectMapper objectMapper;
	
	public CommonTestController() {
		objectMapper = new ObjectMapper()
				.registerModule(new Jdk8Module())
				.registerModule(new JavaTimeModule());;
	}
	

	protected <T> T callPostEndpoint(String endpoint, Object content, int expectedStatusCode, Class<T> clazzReturn) throws Exception {
		 MvcResult mvcResult = this.mockMvc.perform(post(endpoint)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(content)))
				.andExpect(status().is(expectedStatusCode))
				.andReturn();
		
		return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazzReturn);
	}
	
	protected <T> T callGetEndpoint(String endpoint, int expectedStatusCode, Class<T> clazzReturn) throws Exception {
		MvcResult mvcResult = mockMvc.perform(get(endpoint))
				.andExpect(status().is(expectedStatusCode))
				.andReturn();
		
		return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), clazzReturn);
	}
	
}
