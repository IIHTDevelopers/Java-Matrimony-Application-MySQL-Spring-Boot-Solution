package com.matrimonyapplication.functional;

import static com.matrimonyapplication.utils.TestUtils.businessTestFile;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.matrimonyapplication.controller.UserProfileController;

@WebMvcTest(UserProfileController.class)
@AutoConfigureMockMvc
@ActiveProfiles("qa")
public class QAProfileEndpointControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserProfileController userProfileService;

	@BeforeEach
	public void setup() {
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

//	@Test
//	public void testGetQAProfile() throws Exception {
//		String expectedProfileData = "This is qa profile";
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profiles/profile").accept(MediaType.TEXT_PLAIN);
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		System.out.println(result.getResponse().getContentAsString());
//		yakshaAssert(currentTest(),
//				(result.getResponse().getContentAsString().equals(expectedProfileData) ? "true" : "false"),
//				businessTestFile);
//	}
}
