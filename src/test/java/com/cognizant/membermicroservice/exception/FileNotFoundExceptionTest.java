package com.cognizant.membermicroservice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * This class test the FileNotFoundException class
 * 
 *
 */

@SpringBootTest
@RunWith(SpringRunner.class)
 class FileNotFoundExceptionTest {

	@Mock
	private PolicyNotFoundException fileNotFoundException;

	@Test
	public void testOneArgConstructor() {
		PolicyNotFoundException exception = new PolicyNotFoundException("File Not Found.");
		assertEquals("File Not Found.", exception.getMessage());
	}

	@Test
	public void testNoArgConstructor() {
		PolicyNotFoundException exception = new PolicyNotFoundException();
		assertEquals(null, exception.getMessage());
	}
}
