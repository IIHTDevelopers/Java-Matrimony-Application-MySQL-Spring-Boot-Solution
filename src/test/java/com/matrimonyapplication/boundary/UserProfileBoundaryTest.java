package com.matrimonyapplication.boundary;

import static com.matrimonyapplication.utils.TestUtils.boundaryTestFile;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.matrimonyapplication.dto.UserProfileDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UserProfileBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testNameNotNull() throws IOException {
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setName(null);
		Set<ConstraintViolation<UserProfileDTO>> violations = validator.validate(userProfileDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testSexNotNull() throws IOException {
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setSex(null);
		Set<ConstraintViolation<UserProfileDTO>> violations = validator.validate(userProfileDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPhoneNumberNotNull() throws IOException {
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setPhoneNumber(null);
		Set<ConstraintViolation<UserProfileDTO>> violations = validator.validate(userProfileDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAddressNotNull() throws IOException {
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setAddress(null);
		Set<ConstraintViolation<UserProfileDTO>> violations = validator.validate(userProfileDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
