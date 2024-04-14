package com.proj.tracker.controllers;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import com.proj.tracker.dto.ExerciseHistoryDto;
import com.proj.tracker.dto.FetchExercisePayload;
import com.proj.tracker.service.IExerciseHistoryService;

import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
// @AllArgsConstructor
@Validated
public class TrackerService {

    private IExerciseHistoryService IExerciseHistoryService;

    public TrackerService(IExerciseHistoryService IExerciseHistoryService) {
        this.IExerciseHistoryService = IExerciseHistoryService;
    }

    // add exercise history item to db
    @PostMapping("/history")
    public ResponseEntity<?> createExerciseHistory(
            @Valid @RequestBody ExerciseHistoryDto exerciseHistory) {

        IExerciseHistoryService.createExerciseHistory(exerciseHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body("Exercise history created successfully");
    }

    // get all exercise history items
    @GetMapping("/history")
    public List<ExerciseHistoryDto> fetchAllHistory() {

        return IExerciseHistoryService.fetchAllExerciseHistory();
    }

    // delete exercise history item by id
    @DeleteMapping("/history/{id}")
    public ResponseEntity<?> deleteExerciseHistory(
            @PathVariable Long id) {

        if (!IExerciseHistoryService.deleteExerciseHistory(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise history not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Exercise history deleted successfully");
        }
    }

    // get exercise history item by user id, exercise id, and set number
    @GetMapping("/history/{userId}/{exerciseId}/{setNumber}")
    public List<ExerciseHistoryDto> fetchLatestExerciseHistory(
            @PathVariable Long userId, @PathVariable Long exerciseId,
            @PathVariable Integer setNumber) {

        return IExerciseHistoryService.fetchExerciseHistory(userId, exerciseId,
                setNumber);
    }

    // show cloud config server feature
    @GetMapping("/info")
    public String getVersion() {

        return "app working! updated ansi to auto deploy!! trying out multiple hosts!!!!? scanner test!!?!";
    }

}
