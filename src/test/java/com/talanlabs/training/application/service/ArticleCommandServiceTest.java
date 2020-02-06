package com.talanlabs.training.application.service;

import com.talanlabs.training.application.command.PublishArticleCommand;
import com.talanlabs.training.application.command.SubmitArticleCommand;
import com.talanlabs.training.application.service.exception.ArticleNotFoundException;
import com.talanlabs.training.domain.Article;
import com.talanlabs.training.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    void submitArticleCommandStoreModelAsEntity() {
        // given
        SubmitArticleCommand submitArticleCommand = new SubmitArticleCommand("title", "author");
        // When
        articleCommandService.submitArticle(submitArticleCommand);
        // then
        Article expectedModel = new Article(null, "title", "author", 0);
        verify(articleRepository).save(expectedModel);
    }


    @Test
    void publishsArticleCommandWithoutNotFoundArticle() {
        // given
        when(articleRepository.findById(99L)).thenThrow(ArticleNotFoundException.class);
        PublishArticleCommand publishArticleCommand = new PublishArticleCommand(99L, LocalDate.of(2020, 10, 20));
        // When
        assertThrows(ArticleNotFoundException.class, () -> {
            articleCommandService.publishArticle(publishArticleCommand);
        });
    }


    @Test
    void publishsArticleCommandWithoutExistingArticle() {
        // given
        Article expectedArticle = (new Article(4L, "title", "author", 0, null));
        when(articleRepository.findById(4L)).thenReturn(Optional.of(expectedArticle));
        PublishArticleCommand publishArticleCommand = new PublishArticleCommand(4L, LocalDate.of(2020, 10, 20));
        expectedArticle.setPublishedAt(publishArticleCommand.getDate());
        // When
        articleCommandService.publishArticle(publishArticleCommand);
        //then
        verify(articleRepository).save(expectedArticle);
    }
}