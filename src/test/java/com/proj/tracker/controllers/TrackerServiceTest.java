package com.proj.tracker.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.proj.tracker.dto.ExerciseHistoryDto;
import com.proj.tracker.service.IExerciseHistoryService;

public class TrackerServiceTest {

    private IExerciseHistoryService exerciseHistoryServiceMock;

    private TrackerService trackerService;

    @BeforeEach
    public void setup() {
        exerciseHistoryServiceMock = mock(IExerciseHistoryService.class);
        trackerService = new TrackerService(exerciseHistoryServiceMock);
    }

    @Test
    public void testFetchAllHistory() {
        List<ExerciseHistoryDto> historyList = new ArrayList<>();
        when(exerciseHistoryServiceMock.fetchAllExerciseHistory()).thenReturn(historyList);

        List<ExerciseHistoryDto> response = trackerService.fetchAllHistory("correlationId");

        assertEquals(historyList, response);
        verify(exerciseHistoryServiceMock, times(1)).fetchAllExerciseHistory();
    }

    @Test
    public void testCreateExerciseHistory() {
        ExerciseHistoryDto exerciseHistory = new ExerciseHistoryDto();
        doNothing().when(exerciseHistoryServiceMock).createExerciseHistory(exerciseHistory);

        ResponseEntity<?> response = trackerService.createExerciseHistory("correlationId", exerciseHistory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Exercise history created successfully", response.getBody());
        verify(exerciseHistoryServiceMock, times(1)).createExerciseHistory(exerciseHistory);
    }
}
