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
import java.util.List;

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

    @Test
    public void shouldReturnArticleList() throws Exception {

        //when
        List<Article> articles = Arrays.asList(
                new Article(1L, "What is clean code", "Robert C. Martin", 3),
                new Article(2L, "How to use JPQL ? ", "Pivotal", 0),
                new Article(3L, "What is clean code", "Robert C. Martin", 5)
        );
        when(articleQueryUsecase.listAllArticles()).thenReturn(articles);
        //then
        this.mockMvc.perform(
                get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id").value(articles.get(0).getId()))
                .andExpect(jsonPath("$.[0].title").value(articles.get(0).getTitle()))
                .andExpect(jsonPath("$.[0].author").value(articles.get(0).getAuthor()))
                .andExpect(jsonPath("$.[0].version").doesNotExist());
    }

}
