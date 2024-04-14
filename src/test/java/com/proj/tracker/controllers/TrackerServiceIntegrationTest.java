package com.proj.tracker.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TrackerServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateExerciseHistory() throws Exception {
        String exerciseHistoryJson = "{\"userId\":1,\"exerciseId\":1,\"weightInKg\":70.0,\"setNumber\":3,\"repCount\":10}";

        mockMvc.perform(post("/api/history")
                .contentType(MediaType.APPLICATION_JSON)
                .content(exerciseHistoryJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testFetchAllHistory() throws Exception {
        mockMvc.perform(get("/api/history"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteExerciseHistory() throws Exception {
        Long idToDelete = 1L; // replace with a valid ID

        mockMvc.perform(delete("/api/history/" + idToDelete))
                .andExpect(status().isOk());
    }

    @Test
    public void testFetchLatestExerciseHistory() throws Exception {
        Long userId = 1L; // replace with a valid user ID
        Long exerciseId = 1L; // replace with a valid exercise ID
        Integer setNumber = 1; // replace with a valid set number

        mockMvc.perform(get("/api/history/" + userId + "/" + exerciseId + "/" + setNumber))
                .andExpect(status().isOk());
    }
}