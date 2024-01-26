package com.matrimonyapplication.functional;

import static com.matrimonyapplication.utils.TestUtils.businessTestFile;
import static com.matrimonyapplication.utils.TestUtils.currentTest;
import static com.matrimonyapplication.utils.TestUtils.testReport;
import static com.matrimonyapplication.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ActuatorEndpointControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void actuatorMainEndpoint() throws Exception {
		ResponseEntity<String> entity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/matrimonyapplication/actuator", String.class);
		yakshaAssert(currentTest(), entity.getStatusCode() == HttpStatus.OK ? "true" : "false", businessTestFile);
	}

	@Test
	public void healthEndpointEnabled() throws Exception {
		ResponseEntity<String> entity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/matrimonyapplication/actuator/health", String.class);
		yakshaAssert(currentTest(), entity.getStatusCode() == HttpStatus.OK ? "true" : "false", businessTestFile);
	}

	@Test
	public void beansEndpointDisabled() throws Exception {
		ResponseEntity<String> entity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/matrimonyapplication/actuator/beans", String.class);
		yakshaAssert(currentTest(), entity.getStatusCode() == HttpStatus.NOT_FOUND ? "true" : "false",
				businessTestFile);
	}
}
