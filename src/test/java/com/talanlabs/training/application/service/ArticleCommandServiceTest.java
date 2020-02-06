package com.talanlabs.training.application.service;

import com.talanlabs.training.application.command.SubmitArticleCommand;
import com.talanlabs.training.domain.Article;
import com.talanlabs.training.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArticleCommandServiceTest {

    private ArticleCommandService articleCommandService;

    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleCommandService = new ArticleCommandService(articleRepository);
    }

    @Test
    void submitArticleCommandIsStoreAsEntity() {
        // given
        SubmitArticleCommand submitArticleCommand = new SubmitArticleCommand("title", "author");
        // When
        articleCommandService.submitArticle(submitArticleCommand);
        // then
        Article expectedModel = new Article(null, "title", "author", 0);
        verify(articleRepository).save(expectedModel);
    }
}