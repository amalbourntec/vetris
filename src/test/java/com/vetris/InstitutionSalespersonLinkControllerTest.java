package com.vetris;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vetris.apimanagement.ApiManagementApplication;
import com.vetris.apimanagement.v1.controller.InstitutionSalespersonLinkController;
/**
 * @author Aldrin Sunny
 * 
 * Test class for  InstitutionSalespersonLinkController
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(classes = ApiManagementApplication.class) 
@TestPropertySource(value={"classpath:application.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT,classes=InstitutionSalespersonLinkController.class)
@AutoConfigureMockMvc
class InstitutionSalespersonLinkControllerTest {

	@Value("${server.port}") 
	int port;
	 
	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		// build mockMvc 
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetAllInstitutionSalespersonLink() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/v1/salesperson")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print()).andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists());
	      
	}
	
	@Test
	public void testGetInstitutionSalespersonLinkByIdNotFound() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders.get("/v1/salesperson/a12")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print()).andExpect(status().isNotFound());
	        
	}
 
	@Test
	public void testPostInstitutionSalespersonLinkNotFound() throws Exception {
		
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/v1/salesperson")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testPutInstitutionSalespersonLink() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.put("/v1/salesperson/a123")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void testDeleteInstitutionSalespersonLinkNotFound() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/salesperson/a123")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound())
				.andExpect(MockMvcResultMatchers.jsonPath("$.responseMessage").value(" not found"));
	}

}
