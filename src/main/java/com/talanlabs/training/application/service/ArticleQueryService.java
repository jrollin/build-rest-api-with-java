package com.talanlabs.training.application.service;

import com.talanlabs.training.application.query.ArticleQueryUsecase;
import com.talanlabs.training.domain.Article;
import com.talanlabs.training.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleQueryService implements ArticleQueryUsecase {

    private ArticleRepository articleRepository;

    public ArticleQueryService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> listAllArticles() {
        return articleRepository.findAll();
    }
}
