package com.matrimonyapplication.service;

import java.util.List;

import com.matrimonyapplication.dto.UserProfileDTO;

public interface UserProfileService {

	UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO);

	UserProfileDTO getUserProfileById(Long id);

	List<UserProfileDTO> getAllUserProfiles();

	UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO);

	boolean deleteUserProfile(Long id);

	List<UserProfileDTO> searchProfilesBySex(String sex);

	List<UserProfileDTO> searchProfilesByLikesContaining(String likesKeyword);
}
