package com.proj.tracker.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.proj.tracker.dto.ExerciseHistoryDto;
import com.proj.tracker.service.IExerciseHistoryService;

import java.util.Arrays;
import java.util.List;

public class TrackerServiceTest {

    private IExerciseHistoryService exerciseHistoryService = mock(IExerciseHistoryService.class);
    private TrackerService trackerService = new TrackerService(exerciseHistoryService);

    @Test
    public void createExerciseHistoryTest() {
        ExerciseHistoryDto exerciseHistory = new ExerciseHistoryDto();
        doNothing().when(exerciseHistoryService).createExerciseHistory(exerciseHistory);

        ResponseEntity<?> response = trackerService.createExerciseHistory(exerciseHistory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Exercise history created successfully", response.getBody());
    }

    @Test
    public void fetchAllHistoryTest() {
        ExerciseHistoryDto exerciseHistory = new ExerciseHistoryDto();
        when(exerciseHistoryService.fetchAllExerciseHistory()).thenReturn(Arrays.asList(exerciseHistory));

        List<ExerciseHistoryDto> response = trackerService.fetchAllHistory();

        assertEquals(1, response.size());
        assertEquals(exerciseHistory, response.get(0));
    }

    @Test
    public void deleteExerciseHistoryTest() {
        Long id = 1L;
        when(exerciseHistoryService.deleteExerciseHistory(id)).thenReturn(true);

        ResponseEntity<?> response = trackerService.deleteExerciseHistory(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise history deleted successfully", response.getBody());
    }

    @Test
    public void fetchLatestExerciseHistoryTest() {
        Long userId = 1L;
        Long exerciseId = 1L;
        Integer setNumber = 1;
        ExerciseHistoryDto exerciseHistory = new ExerciseHistoryDto();
        when(exerciseHistoryService.fetchExerciseHistory(userId, exerciseId, setNumber))
                .thenReturn(Arrays.asList(exerciseHistory));

        List<ExerciseHistoryDto> response = trackerService.fetchLatestExerciseHistory(userId, exerciseId, setNumber);

        assertEquals(1, response.size());
        assertEquals(exerciseHistory, response.get(0));
    }

    @Test
    public void getVersionTest() {
        String response = trackerService.getVersion();

        assertEquals("This is a test message", response);
    }

    @Test
    public void testDeleteExerciseHistory_NotFound() {
        // Arrange
        IExerciseHistoryService service = mock(IExerciseHistoryService.class);
        when(service.deleteExerciseHistory(anyLong())).thenReturn(false);

        TrackerService controller = new TrackerService(service);

        // Act
        ResponseEntity<?> response = controller.deleteExerciseHistory(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Exercise history not found", response.getBody());
    }

    @Test
    public void testDeleteExerciseHistory_Success() {
        // Arrange
        IExerciseHistoryService service = mock(IExerciseHistoryService.class);
        when(service.deleteExerciseHistory(anyLong())).thenReturn(true);

        TrackerService controller = new TrackerService(service);

        // Act
        ResponseEntity<?> response = controller.deleteExerciseHistory(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Exercise history deleted successfully", response.getBody());
    }

}