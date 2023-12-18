package com.matrimonyapplication.exception;

import static com.matrimonyapplication.utils.MasterData.getUserProfileDTO;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.exceptionTestFile;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.matrimonyapplication.controller.UserProfileController;
import com.matrimonyapplication.dto.UserProfileDTO;
import com.matrimonyapplication.service.UserProfileService;
import com.matrimonyapplication.utils.MasterData;

@WebMvcTest(UserProfileController.class)
@AutoConfigureMockMvc
public class UserProfileExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserProfileService userProfileService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateUserProfileInvalidDataException() throws Exception {
		UserProfileDTO userProfileDTO = getUserProfileDTO();
		userProfileDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/profiles")
				.content(MasterData.asJsonString(userProfileDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateUserProfileInvalidDataException() throws Exception {
		UserProfileDTO userProfileDTO = getUserProfileDTO();
		userProfileDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/profiles/" + userProfileDTO.getId())
				.content(MasterData.asJsonString(userProfileDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetUserProfileByIdResourceNotFoundException() throws Exception {
		UserProfileDTO userProfileDTO = getUserProfileDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User profile not found");

		when(this.userProfileService.getUserProfileById(userProfileDTO.getId()))
				.thenThrow(new ResourceNotFoundException("User profile not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profiles/" + userProfileDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
