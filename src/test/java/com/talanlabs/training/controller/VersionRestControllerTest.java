package com.talanlabs.training.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VersionRestController.class)
class VersionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void shouldReturnVersion() throws Exception {
        this.mockMvc.perform(
                get("/api/version"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.version").value("1.4"));
    }
}