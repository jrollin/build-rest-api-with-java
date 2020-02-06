package com.talanlabs.training.application.service;

import com.talanlabs.training.application.command.ArticleCommandUseCase;
import com.talanlabs.training.application.command.SubmitArticleCommand;
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
        Article article = new Article(null, publishArticleCommand.getTitle(), publishArticleCommand.getAuthor(), 0);
        articleRepository.save(article);
    }
}
