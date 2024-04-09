package com.proj.tracker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FetchExercisePayload {
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Exercise ID is required")
    private Long exerciseId;
    @NotNull(message = "Set number is required")
    private Integer setNumber;
}
