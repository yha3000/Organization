package com.hyeon.organization.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyeon.organization.model.Organization;
import com.hyeon.organization.model.OrganizationType;

@RunWith(SpringRunner.class)
@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
class OrganizationControllerTest {

	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void createEvent() throws Exception {
		Organization organization = Organization.builder()
												.id(1)
												.name("TEST")
												.type(OrganizationType.Company)
												.code("A1234")
												.parent(null)
												.build();
		mockMvc.perform(get("/api/organization")
                		.content(objectMapper.writeValueAsString(organization)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("{\"responseCode\":200,\"responseMsg\":\"success\"}"));
	}

}
