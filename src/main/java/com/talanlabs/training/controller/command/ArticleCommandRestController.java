package com.talanlabs.training.controller.command;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ArticleCommandRestController {

    @PostMapping("/api/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public void submitArticle() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
