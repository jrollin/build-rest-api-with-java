package com.talanlabs.training.application.service;

import com.talanlabs.training.domain.Article;
import com.talanlabs.training.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleQueryServiceTest {


    private ArticleQueryService articleQueryService;

    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleQueryService = new ArticleQueryService(articleRepository);
    }

    @Test
    void listAllArticlesWithNoDataInRepository() {
        // given
        // when
        List<Article> articles = articleQueryService.listAllArticles();
        // then
        assertThat(articles).isEmpty();
    }

    @Test
    void listAllArticlesWithArticlesInRepository() {
        // given
        List<Article> articlesEnities = Arrays.asList(
                new Article(1L, "What is clean code", "Robert C. Martin", 3),
                new Article(2L, "How to use JPQL ? ", "Pivotal", 0),
                new Article(3L, "What is clean code", "Robert C. Martin", 5)
        );
        when(articleRepository.findAll()).thenReturn(articlesEnities);
        // when
        List<Article> articles = articleQueryService.listAllArticles();
        // then
        assertThat(articles).isNotEmpty();
        assertThat(articles.size()).isEqualTo(3);
        assertThat(articles.get(0).getId()).isEqualTo(articlesEnities.get(0).getId());
        assertThat(articles.get(0).getTitle()).isEqualTo(articlesEnities.get(0).getTitle());
        assertThat(articles.get(0).getAuthor()).isEqualTo(articlesEnities.get(0).getAuthor());
        assertThat(articles.get(0).getVersion()).isEqualTo(articlesEnities.get(0).getVersion());

    }
}