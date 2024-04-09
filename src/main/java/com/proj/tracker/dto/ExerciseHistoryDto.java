package com.proj.tracker.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ExerciseHistoryDto {

    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Exercise ID is required")
    @Positive(message = "Exercise ID must be positive integer")
    private Long exerciseId;

    private LocalDate date;

    @NotNull(message = "Weight in kg is required")
    @PositiveOrZero(message = "Weight in kg must be positive or zero")
    private Double weightInKg;

    @NotNull(message = "Set number is required")
    @Positive(message = "Set number must be positive")
    private Integer setNumber;

    @NotNull(message = "Rep count is required")
    @PositiveOrZero(message = "Rep count must be positive")
    private Integer repCount;

}