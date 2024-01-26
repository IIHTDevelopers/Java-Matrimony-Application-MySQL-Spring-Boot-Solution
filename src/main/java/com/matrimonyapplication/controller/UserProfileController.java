package com.matrimonyapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matrimonyapplication.dto.UserProfileDTO;
import com.matrimonyapplication.service.UserProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

	private final UserProfileService userProfileService;

	@Autowired
	private Environment env;

	@Autowired
	public UserProfileController(UserProfileService userProfileService) {
		this.userProfileService = userProfileService;
	}

	@PostMapping
	public ResponseEntity<UserProfileDTO> createUserProfile(@Valid @RequestBody UserProfileDTO userProfileDTO) {
		UserProfileDTO createdProfile = userProfileService.createUserProfile(userProfileDTO);
		return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable Long id) {
		UserProfileDTO userProfileDTO = userProfileService.getUserProfileById(id);
		return new ResponseEntity<>(userProfileDTO, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles() {
		List<UserProfileDTO> userProfiles = userProfileService.getAllUserProfiles();
		return new ResponseEntity<>(userProfiles, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable Long id,
			@Valid @RequestBody UserProfileDTO userProfileDTO) {
		UserProfileDTO updatedProfile = userProfileService.updateUserProfile(id, userProfileDTO);
		return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
		userProfileService.deleteUserProfile(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/sex/{sex}")
	public ResponseEntity<List<UserProfileDTO>> searchProfilesBySex(@PathVariable String sex) {
		List<UserProfileDTO> userProfiles = userProfileService.searchProfilesBySex(sex);
		return new ResponseEntity<>(userProfiles, HttpStatus.OK);
	}

	@GetMapping("/search/likes/{likesKeyword}")
	public ResponseEntity<List<UserProfileDTO>> searchProfilesByLikesContaining(@PathVariable String likesKeyword) {
		List<UserProfileDTO> userProfiles = userProfileService.searchProfilesByLikesContaining(likesKeyword);
		return new ResponseEntity<>(userProfiles, HttpStatus.OK);
	}

	@GetMapping("/profile")
	public ResponseEntity<String> getProfile() {
		String profileData = env.getProperty("profile.validate.data", "This is default profile");
		return new ResponseEntity<>(profileData, HttpStatus.OK);
	}
}
