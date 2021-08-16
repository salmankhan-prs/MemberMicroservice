package com.cognizant.membermicroservice.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
 class ErrorMessageTest {
    @Mock
    private ErrorMessage errorMessage;
    @Test
    @DisplayName("Checking if ErrorMessage class is loading or not.")
    void ErrorMessageIsLoadedOrNot() {
        assertThat(errorMessage).isNotNull();
    }


    @DisplayName("Checking if ErrorMessage class is responding correctly or not.")
    @Test
    void testingErrorMessage() {
        errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, "Not found");
        errorMessage.setHttpStatus(HttpStatus.NOT_FOUND);
        errorMessage.setMessage("Not found");


        assertEquals(HttpStatus.NOT_FOUND, errorMessage.getHttpStatus());
        assertEquals("Not found", errorMessage.getMessage());
    }


}
