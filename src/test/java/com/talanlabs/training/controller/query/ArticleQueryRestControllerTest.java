package com.talanlabs.training.controller.query;

import com.talanlabs.training.application.query.ArticleQueryUsecase;
import com.talanlabs.training.domain.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleQueryRestController.class)
class ArticleQueryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleQueryUsecase articleQueryUsecase;

    @Test
    public void shouldReturnEmptyList() throws Exception {
        //when
        when(articleQueryUsecase.listAllArticles()).thenReturn(Collections.emptyList());
        //then
        this.mockMvc.perform(
                get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
