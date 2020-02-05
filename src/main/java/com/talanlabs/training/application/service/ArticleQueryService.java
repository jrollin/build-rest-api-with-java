package com.talanlabs.training.application.service;

import com.talanlabs.training.application.query.ArticleQueryUsecase;
import com.talanlabs.training.domain.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleQueryService implements ArticleQueryUsecase {

    @Override
    public List<Article> listAllArticles() {
        return null;
    }
}
