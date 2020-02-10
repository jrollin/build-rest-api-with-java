package com.talanlabs.training.application.service;

import com.talanlabs.training.application.command.ArticleCommandUseCase;
import com.talanlabs.training.application.command.PublishArticleCommand;
import com.talanlabs.training.application.command.SubmitArticleCommand;
import com.talanlabs.training.application.service.exception.ArticleNotFoundException;
import com.talanlabs.training.domain.Article;
import com.talanlabs.training.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleCommandService implements ArticleCommandUseCase {

    private ArticleRepository articleRepository;

    public ArticleCommandService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void submitArticle(SubmitArticleCommand publishArticleCommand) {
        Article article = new Article(
                null,
                publishArticleCommand.getTitle(),
                publishArticleCommand.getAuthor(),
                0);
        articleRepository.save(article);
    }

    @Override
    public void publishArticle(PublishArticleCommand publishArticleCommand) {
        Long articleId = publishArticleCommand.getId();
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));

        article.setPublishedAt(publishArticleCommand.getDate());

        articleRepository.save(article);
    }

}
