package com.talanlabs.training.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
@ContextConfiguration
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should display unauthorized")
    public void shouldReturn401() throws Exception {
        this.mockMvc.perform(
                get("/api/user"))
                .andExpect(status().isUnauthorized());
    }


    @WithUserDetails()
    @Test
    @DisplayName("should display User details Infos no args ")
    public void shouldReturnUserInfosDefault() throws Exception {
        this.mockMvc.perform(
                get("/api/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.username").value("user"))
                .andExpect(jsonPath("$.roles").value("[ROLE_USER]"));
    }

    @WithUserDetails("journalist")
    @Test
    @DisplayName("should display User details Infos journalist ")
    public void shouldReturnUserInfosJournalist() throws Exception {
        this.mockMvc.perform(
                get("/api/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.username").value("journalist"))
                .andExpect(jsonPath("$.roles").value("[ROLE_WRITER]"));
    }


    @WithUserDetails("redactor")
    @Test
    @DisplayName("should display User details redactor")
    public void shouldReturnUserInfosRedactor() throws Exception {
        this.mockMvc.perform(
                get("/api/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.username").value("redactor"))
                .andExpect(jsonPath("$.roles").value("[ROLE_PUBLISHER]"));
    }


    @WithMockUser(username = "superman", roles = {"ADMIN"})
    @Test
    @DisplayName("should display User redactor mock")
    public void shouldReturnMockRedactor() throws Exception {
        this.mockMvc.perform(
                get("/api/user"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.username").value("superman"))
                .andExpect(jsonPath("$.roles").value("[ROLE_ADMIN]"));

    }
}