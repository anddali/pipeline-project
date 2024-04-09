package com.proj.tracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exercise_history")
public class ExerciseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "exercise_id")
    private Long exerciseId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "weight_in_kg")
    private Double weightInKg;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "rep_count")
    private Integer repCount;

    @PrePersist
    public void prePersist() {
        // Set the current date before persisting the entity
        this.date = LocalDate.now();
    }

}
