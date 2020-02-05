package com.talanlabs.training.controller.query;

import com.talanlabs.training.application.presentation.ArticleDto;
import com.talanlabs.training.application.query.ArticleQueryUsecase;
import com.talanlabs.training.domain.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticleQueryRestController {

    private final ArticleQueryUsecase articleQueryUsecase;

    public ArticleQueryRestController(ArticleQueryUsecase articleQueryUsecase) {
        this.articleQueryUsecase = articleQueryUsecase;
    }

    @GetMapping("/api/articles")
    public List<ArticleDto> getArticles() {

        List<Article> articles = articleQueryUsecase.listAllArticles();
        List<ArticleDto> articlesDto = articles.stream()
                .map(ArticleDto::fromDomain)
                .collect(Collectors.toList());

        return articlesDto;
    }
}
