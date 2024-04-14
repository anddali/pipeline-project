package com.proj.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.proj.tracker.exception.ErrorResponse;
import com.proj.tracker.exception.ResourceNotFoundException;

public class ApplicationExceptionHandlerTest {

    private ApplicationExceptionHandler handler = new ApplicationExceptionHandler();

    @Test
    public void handleDataAccessExceptionTest() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Not found");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleDataAccessException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ErrorResponse expectedResponse = new ErrorResponse(Arrays.asList("Not found"));
        ErrorResponse actualResponse = (ErrorResponse) response.getBody();
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage()); // assuming getErrors() method exists
    }

    @Test
    public void handleMethodArgumentNotValidTest() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>()); // return empty list for simplicity

        HttpHeaders headers = mock(HttpHeaders.class);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<Object> response = handler.handleMethodArgumentNotValid(ex, headers, status, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        // Add more assertions for the error response body as needed
    }
}