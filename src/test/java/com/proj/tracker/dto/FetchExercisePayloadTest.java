package com.proj.tracker.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class FetchExercisePayloadTest {

    @Test
    public void testFetchExercisePayload() {
        FetchExercisePayload payload = new FetchExercisePayload();
        payload.setUserId(1L);
        payload.setExerciseId(2L);
        payload.setSetNumber(3);

        assertEquals(1L, payload.getUserId());
        assertEquals(2L, payload.getExerciseId());
        assertEquals(3, payload.getSetNumber());
    }

    @Test
    public void testValidation() {
        FetchExercisePayload payload = new FetchExercisePayload();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        assertFalse(validator.validate(payload).isEmpty());

        payload.setUserId(1L);
        payload.setExerciseId(2L);
        payload.setSetNumber(3);
        assertTrue(validator.validate(payload).isEmpty());
    }

    @Test
    public void testEqualsAndHashCode() {
        FetchExercisePayload payload1 = new FetchExercisePayload();
        payload1.setUserId(1L);
        payload1.setExerciseId(2L);
        payload1.setSetNumber(3);

        FetchExercisePayload payload2 = new FetchExercisePayload();
        payload2.setUserId(1L);
        payload2.setExerciseId(2L);
        payload2.setSetNumber(3);

        assertTrue(payload1.equals(payload2) && payload2.equals(payload1));
        assertEquals(payload1.hashCode(), payload2.hashCode());
    }

    @Test
    public void testToString() {
        FetchExercisePayload payload = new FetchExercisePayload();
        payload.setUserId(1L);
        payload.setExerciseId(2L);
        payload.setSetNumber(3);

        String expectedString = "FetchExercisePayload(userId=1, exerciseId=2, setNumber=3)";
        assertEquals(expectedString, payload.toString());
    }
}