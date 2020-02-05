package com.talanlabs.training.application.query;

import com.talanlabs.training.domain.Article;

import java.util.List;

public interface ArticleQueryUsecase {

    List<Article> listAllArticles();
}
