package com.proj.tracker.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.proj.tracker.exception.ErrorResponse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class ErrorResponseTest {

    @Test
    public void testErrorResponse() {
        List<String> message = Arrays.asList("Error message");
        ErrorResponse errorResponse = new ErrorResponse(message);

        assertNotNull(errorResponse.getTimestamp());
        assertEquals(message, errorResponse.getMessage());

        LocalDateTime newTimestamp = LocalDateTime.now();
        errorResponse.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, errorResponse.getTimestamp());

        List<String> newMessage = Arrays.asList("New error message");
        errorResponse.setMessage(newMessage);
        assertEquals(newMessage, errorResponse.getMessage());
    }
}