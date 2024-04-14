package com.proj.tracker.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.proj.tracker.dao.TrackerRepository;
import com.proj.tracker.dto.ExerciseHistoryDto;
import com.proj.tracker.entity.ExerciseHistory;
import com.proj.tracker.exception.ResourceNotFoundException;
import com.proj.tracker.mapper.ExerciseHistoryMapper;
import com.proj.tracker.service.impl.ExerciseHistoryServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExerciseHistoryServiceImplTest {

    private TrackerRepository trackerRepository = mock(TrackerRepository.class);
    private ExerciseHistoryServiceImpl exerciseHistoryService = new ExerciseHistoryServiceImpl(trackerRepository);

    @Test
    public void createExerciseHistoryTest() {
        ExerciseHistoryDto exerciseHistoryDto = new ExerciseHistoryDto();
        ExerciseHistory exerciseHistory = ExerciseHistoryMapper.mapToExerciseHistory(exerciseHistoryDto);
        when(trackerRepository.save(any(ExerciseHistory.class))).thenReturn(exerciseHistory);

        exerciseHistoryService.createExerciseHistory(exerciseHistoryDto);

        verify(trackerRepository, times(1)).save(any(ExerciseHistory.class));
    }

    @Test
    public void fetchExerciseHistoryTest() {
        Long userId = 1L;
        Long exerciseId = 1L;
        Integer setNumber = 1;
        ExerciseHistory exerciseHistory = new ExerciseHistory();
        when(trackerRepository.findByUserIdAndExerciseIdAndSetNumberOrderByDateDesc(userId, exerciseId, setNumber))
                .thenReturn(Arrays.asList(exerciseHistory));

        List<ExerciseHistoryDto> response = exerciseHistoryService.fetchExerciseHistory(userId, exerciseId, setNumber);

        assertEquals(1, response.size());
        assertEquals(ExerciseHistoryMapper.mapToExerciseHistoryDto(exerciseHistory), response.get(0));
    }

    @Test
    public void fetchAllExerciseHistoryTest() {
        ExerciseHistory exerciseHistory = new ExerciseHistory();
        when(trackerRepository.findAll()).thenReturn(Arrays.asList(exerciseHistory));

        List<ExerciseHistoryDto> response = exerciseHistoryService.fetchAllExerciseHistory();

        assertEquals(1, response.size());
        assertEquals(ExerciseHistoryMapper.mapToExerciseHistoryDto(exerciseHistory), response.get(0));
    }

    @Test
    public void deleteExerciseHistoryTest() {
        Long id = 1L;
        when(trackerRepository.existsById(id)).thenReturn(true);

        boolean response = exerciseHistoryService.deleteExerciseHistory(id);

        assertTrue(response);
        verify(trackerRepository, times(1)).deleteById(id);
    }

    @Test
    public void deleteExerciseHistoryNotFoundTest() {
        Long id = 1L;
        when(trackerRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> exerciseHistoryService.deleteExerciseHistory(id));
    }
}