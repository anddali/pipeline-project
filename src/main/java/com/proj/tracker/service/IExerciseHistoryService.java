package com.proj.tracker.service;

import com.proj.tracker.dto.ExerciseHistoryDto;
import java.util.List;

public interface IExerciseHistoryService {

    void createExerciseHistory(ExerciseHistoryDto exerciseHistory);

    List<ExerciseHistoryDto> fetchExerciseHistory(Long userId, Long exerciseId, Integer setNumber);

    List<ExerciseHistoryDto> fetchAllExerciseHistory();

    boolean deleteExerciseHistory(Long id);

}
