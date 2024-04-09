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
import jakarta.ws.rs.HeaderParam;
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

    private static final Logger logger = (Logger) LoggerFactory.getLogger(TrackerService.class);
    private IExerciseHistoryService IExerciseHistoryService;

    public TrackerService(IExerciseHistoryService IExerciseHistoryService) {
        this.IExerciseHistoryService = IExerciseHistoryService;
    }

    // showing cloud config server
    @Value("${build.version}")
    private String buildVersion;
    @Value("${details.description}")
    private String description;
    @Value("${details.author}")
    private String author;

    // add exercise history item to db
    @PostMapping("/history")
    public ResponseEntity<?> createExerciseHistory(@RequestHeader("correlation-id") String correlationId,
            @Valid @RequestBody ExerciseHistoryDto exerciseHistory) {
        logger.debug("CORRELATION ID: " + correlationId);
        IExerciseHistoryService.createExerciseHistory(exerciseHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body("Exercise history created successfully");
    }

    // get all exercise history items
    @GetMapping("/history")
    public List<ExerciseHistoryDto> fetchAllHistory(@RequestHeader("correlation-id") String correlationId) {
        logger.debug("CORRELATION ID: " + correlationId);
        return IExerciseHistoryService.fetchAllExerciseHistory();
    }

    // delete exercise history item by id
    @DeleteMapping("/history/{id}")
    public ResponseEntity<?> deleteExerciseHistory(@RequestHeader("correlation-id") String correlationId,
            @PathVariable Long id) {
        logger.debug("CORRELATION ID: " + correlationId);
        if (!IExerciseHistoryService.deleteExerciseHistory(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise history not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Exercise history deleted successfully");
        }
    }

    // get exercise history item by user id, exercise id, and set number
    @GetMapping("/history/{userId}/{exerciseId}/{setNumber}")
    public List<ExerciseHistoryDto> fetchLatestExerciseHistory(@RequestHeader("correlation-id") String correlationId,
            @PathVariable Long userId, @PathVariable Long exerciseId,
            @PathVariable Integer setNumber) {
        logger.debug("CORRELATION ID: " + correlationId);
        return IExerciseHistoryService.fetchExerciseHistory(userId, exerciseId,
                setNumber);
    }

    // show cloud config server feature
    @GetMapping("/info")
    public String getVersion(@RequestHeader("correlation-id") String correlationId) {
        logger.debug("CORRELATION ID: " + correlationId);
        return description + ". Author: " + author + ". Build: " + buildVersion;
    }

}
