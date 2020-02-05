package com.talanlabs.training.controller.command;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ArticleCommandRestController.class)
public class ArticleCommandRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnErrorHeaderWithNoBody() throws Exception {
        //when
        //then
        this.mockMvc.perform(
                post("/api/articles"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());
    }
}
