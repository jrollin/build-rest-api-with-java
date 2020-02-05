package com.talanlabs.training.controller.query;

import com.talanlabs.training.application.query.ArticleQueryUsecase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ArticleQueryRestController {

    private final ArticleQueryUsecase articleQueryUsecase;

    public ArticleQueryRestController(ArticleQueryUsecase articleQueryUsecase) {
        this.articleQueryUsecase = articleQueryUsecase;
    }

    @GetMapping("/api/articles")
    public List<Object> getArticles() {
        return articleQueryUsecase.listAllArticles();
    }
}
