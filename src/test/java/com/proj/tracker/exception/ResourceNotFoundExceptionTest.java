package com.proj.tracker.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.proj.tracker.exception.ResourceNotFoundException;

public class ResourceNotFoundExceptionTest {

    @Test
    public void testResourceNotFoundExceptionWithThreeArgs() {
        String resourceName = "Resource";
        String fieldName = "Field";
        String fieldValue = "Value";
        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

        String expectedMessage = "Resource not found with the given input data Field : 'Value'";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testResourceNotFoundExceptionWithOneArg() {
        String message = "Error message";
        ResourceNotFoundException exception = new ResourceNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }
}