package com.proj.tracker.mapper;

import com.proj.tracker.dto.ExerciseHistoryDto;
import com.proj.tracker.entity.ExerciseHistory;

public class ExerciseHistoryMapper {

    public static ExerciseHistoryDto mapToExerciseHistoryDto(ExerciseHistory exerciseHistory) {
        ExerciseHistoryDto dto = new ExerciseHistoryDto();
        dto.setId(exerciseHistory.getId());
        dto.setUserId(exerciseHistory.getUserId());
        dto.setExerciseId(exerciseHistory.getExerciseId());
        dto.setDate(exerciseHistory.getDate());
        dto.setWeightInKg(exerciseHistory.getWeightInKg());
        dto.setSetNumber(exerciseHistory.getSetNumber());
        dto.setRepCount(exerciseHistory.getRepCount());
        return dto;
    }

    public static ExerciseHistory mapToExerciseHistory(ExerciseHistoryDto dto) {
        ExerciseHistory exerciseHistory = new ExerciseHistory();
        exerciseHistory.setId(dto.getId());
        exerciseHistory.setUserId(dto.getUserId());
        exerciseHistory.setExerciseId(dto.getExerciseId());
        exerciseHistory.setDate(dto.getDate());
        exerciseHistory.setWeightInKg(dto.getWeightInKg());
        exerciseHistory.setSetNumber(dto.getSetNumber());
        exerciseHistory.setRepCount(dto.getRepCount());
        return exerciseHistory;
    }
}
