package com.talanlabs.training.application.service;

import com.talanlabs.training.application.query.ArticleQueryUsecase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleQueryService implements ArticleQueryUsecase {

    @Override
    public List<Object> listAllArticles() {
        return null;
    }
}
