package com.proj.tracker.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.proj.tracker.entity.ExerciseHistory;

@DataJpaTest
public class ExerciseHistoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testPrePersist() {
        ExerciseHistory history = new ExerciseHistory(null, 1L, 1L, null, 70.0, 3, 10);

        // Save the entity to the database
        history = entityManager.persistAndFlush(history);

        // Retrieve the entity from the database
        ExerciseHistory retrievedHistory = entityManager.find(ExerciseHistory.class, history.getId());

        // Verify that the @PrePersist method was called
        assertNotNull(retrievedHistory.getDate());
    }
}