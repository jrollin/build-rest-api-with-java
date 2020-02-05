package com.talanlabs.training.controller.query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ArticleQueryRestController {

    @GetMapping("/api/articles")
    public List<Object> getArticles() {
        return Collections.emptyList();
    }
}
