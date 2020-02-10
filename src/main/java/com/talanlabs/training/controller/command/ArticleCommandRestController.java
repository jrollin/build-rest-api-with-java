package com.talanlabs.training.controller.command;

import com.talanlabs.training.application.command.ArticleCommandUseCase;
import com.talanlabs.training.application.command.PublishArticleCommand;
import com.talanlabs.training.application.command.SubmitArticleCommand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(produces = {"application/json;charset=utf-8"})
public class ArticleCommandRestController {


    private ArticleCommandUseCase articleCommandUseCase;

    public ArticleCommandRestController(ArticleCommandUseCase articleCommandUseCase) {
        this.articleCommandUseCase = articleCommandUseCase;
    }

    @PostMapping("/api/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public void submitArticle(@Valid @RequestBody SubmitArticleCommand submitArticleCommand) {
        articleCommandUseCase.submitArticle(submitArticleCommand);
    }

    @PutMapping("/api/articles/{id}/publish/{date}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void publishArticle(@PathVariable Long id, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        PublishArticleCommand publishArticleCommand = new PublishArticleCommand(id, date);
        articleCommandUseCase.publishArticle(publishArticleCommand);
    }

}
