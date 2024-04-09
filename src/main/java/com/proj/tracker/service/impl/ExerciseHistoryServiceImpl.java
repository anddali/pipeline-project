package com.proj.tracker.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.proj.tracker.dao.TrackerRepository;
import com.proj.tracker.dto.ExerciseHistoryDto;
import com.proj.tracker.entity.ExerciseHistory;
import com.proj.tracker.exception.ResourceNotFoundException;
import com.proj.tracker.service.IExerciseHistoryService;
import com.proj.tracker.mapper.ExerciseHistoryMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExerciseHistoryServiceImpl implements IExerciseHistoryService {
    private TrackerRepository trackerRepository;

    @Override
    public void createExerciseHistory(ExerciseHistoryDto exerciseHistory) {
        System.out.println("Creating exercise history" + exerciseHistory.toString());
        trackerRepository.save(ExerciseHistoryMapper.mapToExerciseHistory(exerciseHistory));
    }

    @Override
    public List<ExerciseHistoryDto> fetchExerciseHistory(Long userId, Long exerciseId, Integer setNumber) {
        List<ExerciseHistory> exerciseHistories = trackerRepository
                .findByUserIdAndExerciseIdAndSetNumberOrderByDateDesc(userId, exerciseId, setNumber);
        if (exerciseHistories.isEmpty()) {
            return new ArrayList<>();
        } else {
            return exerciseHistories.stream()
                    .map(ExerciseHistoryMapper::mapToExerciseHistoryDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<ExerciseHistoryDto> fetchAllExerciseHistory() {
        return ((Collection<ExerciseHistory>) trackerRepository.findAll()).stream()
                .map(ExerciseHistoryMapper::mapToExerciseHistoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteExerciseHistory(Long id) {
        if (!trackerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exercise history not found for id: " + id);
        } else {
            trackerRepository.deleteById(id);
            return true;
        }
    }

}
