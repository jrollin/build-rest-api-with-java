package com.talanlabs.training.controller.command;

import com.talanlabs.training.application.command.ArticleCommandUseCase;
import com.talanlabs.training.application.command.PublishArticleCommand;
import com.talanlabs.training.application.command.SubmitArticleCommand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
