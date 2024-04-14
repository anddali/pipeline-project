package com.proj.tracker.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import com.proj.tracker.entity.ExerciseHistory;

public class ExerciseHistoryTest {

    @Test
    public void testEqualsAndHashCode() {
        ExerciseHistory history1 = new ExerciseHistory(1L, 1L, 1L, LocalDate.now(), 70.0, 3, 10);
        ExerciseHistory history2 = new ExerciseHistory(1L, 1L, 1L, LocalDate.now(), 70.0, 3, 10);

        assertTrue(history1.equals(history2) && history2.equals(history1));
        assertEquals(history1.hashCode(), history2.hashCode());
    }

    @Test
    public void testToString() {
        ExerciseHistory history = new ExerciseHistory(1L, 1L, 1L, LocalDate.now(), 70.0, 3, 10);

        String expectedString = "ExerciseHistory(id=1, userId=1, exerciseId=1, date=" + LocalDate.now()
                + ", weightInKg=70.0, setNumber=3, repCount=10)";
        assertEquals(expectedString, history.toString());
    }

    @Test
    public void testGettersAndSetters() {
        ExerciseHistory history = new ExerciseHistory();
        history.setId(1L);
        history.setUserId(1L);
        history.setExerciseId(1L);
        history.setDate(LocalDate.now());
        history.setWeightInKg(70.0);
        history.setSetNumber(3);
        history.setRepCount(10);

        assertEquals(1L, history.getId());
        assertEquals(1L, history.getUserId());
        assertEquals(1L, history.getExerciseId());
        assertEquals(LocalDate.now(), history.getDate());
        assertEquals(70.0, history.getWeightInKg());
        assertEquals(3, history.getSetNumber());
        assertEquals(10, history.getRepCount());
    }

}