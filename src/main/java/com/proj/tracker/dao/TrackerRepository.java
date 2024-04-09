package com.proj.tracker.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proj.tracker.entity.ExerciseHistory;

@Repository
public interface TrackerRepository extends CrudRepository<ExerciseHistory, Long> {

        List<ExerciseHistory> findByUserIdAndExerciseIdOrderByDateDesc(Integer userId, Integer exerciseId);

        List<ExerciseHistory> findByUserId(Integer userId);

        List<ExerciseHistory> findByUserIdAndExerciseIdAndSetNumberOrderByDateDesc(
                        Long userId,
                        Long exerciseId,
                        Integer setNumber);

        List<ExerciseHistory> findByUserIdAndExerciseIdAndSetNumberAndDateOrderByDateDesc(Integer userId,
                        Integer exerciseId,
                        Integer setNumber, LocalDate now);

        void deleteById(Integer id);

}
