package com.talanlabs.training.application.service.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(Long id) {
        super(String.format("Article not found with id %s ", id));
    }
}
