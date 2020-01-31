package com.talanlabs.training.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
                .andExpect(content().string(containsString("version")));
    }
}