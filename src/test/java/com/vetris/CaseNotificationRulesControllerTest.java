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

import com.vetris.adminmanagement.AdminManagementApplication;
import com.vetris.adminmanagement.v1.contoller.CaseNotificationRulesController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdminManagementApplication.class)
@TestPropertySource(value = { "classpath:application.properties" })
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = CaseNotificationRulesController.class)
@AutoConfigureMockMvc
public class CaseNotificationRulesControllerTest {

	@Value("${server.port}")
	int port;

	@Autowired
	private WebApplicationContext webApplicationContext;

	protected MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

//Composite keys input required.
	@Test
	public void getAllCaseNotiRules() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/v1/case_notification_rules/get_all/?priorityId=100&ruleNo=2&pacsStatusId=1")
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").exists());
	}

	@Test
	public void putCaseNotiRules() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.put("/v1/case_notification_rules/?priorityId=100&ruleNo=2&pacsStatusId=1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void postCaseNotiRulesNotFound() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/case_notification_rules").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());
	}

	@Test
	public void deleteCaseNotiRuleNotFound() throws Exception {

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/v1/case_notification_rules/?priorityId=100&ruleNo=1&pacsStatusId=1")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound()).andExpect(
						MockMvcResultMatchers.jsonPath("$.responseMessage").value("Case notification rules not found"));
	}

}