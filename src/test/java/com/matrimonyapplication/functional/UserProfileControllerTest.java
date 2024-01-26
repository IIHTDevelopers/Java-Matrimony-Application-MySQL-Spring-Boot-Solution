package com.matrimonyapplication.functional;

import static com.matrimonyapplication.utils.MasterData.asJsonString;
import static com.matrimonyapplication.utils.MasterData.getUserProfileDTO;
import static com.matrimonyapplication.utils.MasterData.getUserProfileDTOList;
import static com.matrimonyapplication.utils.TestUtils.businessTestFile;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.matrimonyapplication.controller.UserProfileController;
import com.matrimonyapplication.dto.UserProfileDTO;
import com.matrimonyapplication.service.UserProfileService;

@WebMvcTest(UserProfileController.class)
@AutoConfigureMockMvc
public class UserProfileControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserProfileService userProfileService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllUserProfiles() throws Exception {
		List<UserProfileDTO> userProfileDTOs = getUserProfileDTOList();

		when(this.userProfileService.getAllUserProfiles()).thenReturn(userProfileDTOs);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profiles")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(userProfileDTOs)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetUserProfileById() throws Exception {
		UserProfileDTO userProfileDTO = getUserProfileDTO();
		when(this.userProfileService.getUserProfileById(userProfileDTO.getId())).thenReturn(userProfileDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profiles/" + userProfileDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(userProfileDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testCreateUserProfile() throws Exception {
		UserProfileDTO userProfileDTO = getUserProfileDTO();

		when(this.userProfileService.createUserProfile(any())).thenReturn(userProfileDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/profiles")
				.content(asJsonString(userProfileDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(userProfileDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdateUserProfile() throws Exception {
		UserProfileDTO userProfileDTO = getUserProfileDTO();

		when(this.userProfileService.updateUserProfile(eq(userProfileDTO.getId()), any())).thenReturn(userProfileDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/profiles/" + userProfileDTO.getId())
				.content(asJsonString(userProfileDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(userProfileDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testDeleteUserProfile() throws Exception {
		UserProfileDTO userProfileDTO = getUserProfileDTO();
		when(this.userProfileService.deleteUserProfile(userProfileDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/profiles/" + userProfileDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testSearchProfilesBySex() throws Exception {
		String sex = "Male";
		List<UserProfileDTO> userProfileDTOList = getUserProfileDTOList();

		when(this.userProfileService.searchProfilesBySex(sex)).thenReturn(userProfileDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profiles/search/sex/" + sex)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(userProfileDTOList)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testSearchProfilesByLikesContaining() throws Exception {
		String likesKeyword = "Reading";
		List<UserProfileDTO> userProfileDTOList = getUserProfileDTOList();

		when(this.userProfileService.searchProfilesByLikesContaining(likesKeyword)).thenReturn(userProfileDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profiles/search/likes/" + likesKeyword)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(userProfileDTOList)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetProfile() throws Exception {
		String expectedProfileData = "This is default profile";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/profiles/profile").accept(MediaType.TEXT_PLAIN);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().equals(expectedProfileData) ? "true" : "false"),
				businessTestFile);
	}
}
