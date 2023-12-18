package com.matrimonyapplication.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.matrimonyapplication.dto.UserProfileDTO;

public class MasterData {

	public static UserProfileDTO getUserProfileDTO() {
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setId(1L);
		userProfileDTO.setName("John Doe");
		userProfileDTO.setSex("Male");
		userProfileDTO.setPhoneNumber("1234567890");
		userProfileDTO.setAddress("123 Main St");
		userProfileDTO.setLikes("Reading");
		userProfileDTO.setDislikes("Loud noises");

		return userProfileDTO;
	}

	public static List<UserProfileDTO> getUserProfileDTOList() {
		List<UserProfileDTO> userProfileDTOList = new ArrayList<>();

		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setId(1L);
		userProfileDTO.setName("John Doe");
		userProfileDTO.setSex("Male");
		userProfileDTO.setPhoneNumber("1234567890");
		userProfileDTO.setAddress("123 Main St");
		userProfileDTO.setLikes("Reading");
		userProfileDTO.setDislikes("Loud noises");

		userProfileDTOList.add(userProfileDTO);

		return userProfileDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
