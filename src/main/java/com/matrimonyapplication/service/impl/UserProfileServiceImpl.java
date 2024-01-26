package com.matrimonyapplication.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrimonyapplication.dto.UserProfileDTO;
import com.matrimonyapplication.entity.UserProfile;
import com.matrimonyapplication.exception.ResourceNotFoundException;
import com.matrimonyapplication.repo.UserProfileRepository;
import com.matrimonyapplication.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileRepository userProfileRepository;

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public UserProfileDTO createUserProfile(UserProfileDTO userProfileDTO) {
		UserProfile userProfile = new UserProfile();
		BeanUtils.copyProperties(userProfileDTO, userProfile);
		UserProfile savedProfile = userProfileRepository.save(userProfile);
		UserProfileDTO savedDTO = new UserProfileDTO();
		BeanUtils.copyProperties(savedProfile, savedDTO);
		return savedDTO;
	}

	@Override
	public UserProfileDTO getUserProfileById(Long id) {
		Optional<UserProfile> userProfileOptional = userProfileRepository.findById(id);
		if (userProfileOptional.isPresent()) {
			UserProfileDTO userProfileDTO = new UserProfileDTO();
			BeanUtils.copyProperties(userProfileOptional.get(), userProfileDTO);
			return userProfileDTO;
		} else {
			throw new ResourceNotFoundException("User profile not found");
		}
	}

	@Override
	public List<UserProfileDTO> getAllUserProfiles() {
		List<UserProfile> userProfiles = userProfileRepository.findAll();
		return userProfiles.stream().map(userProfile -> {
			UserProfileDTO userProfileDTO = new UserProfileDTO();
			BeanUtils.copyProperties(userProfile, userProfileDTO);
			return userProfileDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public UserProfileDTO updateUserProfile(Long id, UserProfileDTO userProfileDTO) {
		Optional<UserProfile> userProfileOptional = userProfileRepository.findById(id);
		if (userProfileOptional.isPresent()) {
			UserProfile userProfile = userProfileOptional.get();
			BeanUtils.copyProperties(userProfileDTO, userProfile);
			UserProfile updatedProfile = userProfileRepository.save(userProfile);
			UserProfileDTO updatedDTO = new UserProfileDTO();
			BeanUtils.copyProperties(updatedProfile, updatedDTO);
			return updatedDTO;
		} else {
			throw new ResourceNotFoundException("User profile not found");
		}
	}

	@Override
	public boolean deleteUserProfile(Long id) {
		Optional<UserProfile> userProfileOptional = userProfileRepository.findById(id);
		if (userProfileOptional.isPresent()) {
			userProfileRepository.deleteById(id);
			return true;
		} else {
			throw new ResourceNotFoundException("User profile not found");
		}
	}

	@Override
	public List<UserProfileDTO> searchProfilesBySex(String sex) {
		List<UserProfile> userProfiles = userProfileRepository.findAllBySex(sex);
		return userProfiles.stream().map(userProfile -> {
			UserProfileDTO userProfileDTO = new UserProfileDTO();
			BeanUtils.copyProperties(userProfile, userProfileDTO);
			return userProfileDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public List<UserProfileDTO> searchProfilesByLikesContaining(String likesKeyword) {
		List<UserProfile> userProfiles = userProfileRepository.findByLikesContaining(likesKeyword);
		return userProfiles.stream().map(userProfile -> {
			UserProfileDTO userProfileDTO = new UserProfileDTO();
			BeanUtils.copyProperties(userProfile, userProfileDTO);
			return userProfileDTO;
		}).collect(Collectors.toList());
	}
}
