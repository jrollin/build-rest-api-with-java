package com.talanlabs.training.controller.command;

import com.talanlabs.training.application.command.ArticleCommandUseCase;
import com.talanlabs.training.application.command.PublishArticleCommand;
import com.talanlabs.training.application.command.SubmitArticleCommand;
import com.talanlabs.training.controller.exception.ValidationFailedException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(produces = {"application/json;charset=utf-8"})
public class ArticleCommandRestController {


    private ArticleCommandUseCase articleCommandUseCase;

    public ArticleCommandRestController(ArticleCommandUseCase articleCommandUseCase) {
        this.articleCommandUseCase = articleCommandUseCase;
    }

    @PostMapping("/api/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public void submitArticle(@RequestBody Map<String, String> data) {

        SubmitArticleCommand submitArticleCommand = new SubmitArticleCommand(data.get("title"), data.get("author"));

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SubmitArticleCommand>> constraintViolations = validator.validate(submitArticleCommand);
        if (!constraintViolations.isEmpty()) {
            throw new ValidationFailedException(new HashSet<ConstraintViolation<?>>(constraintViolations));
        }

        articleCommandUseCase.submitArticle(submitArticleCommand);
    }

    @PutMapping("/api/articles/{id}/publish/{date}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void publishArticle(@PathVariable Long id, @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        PublishArticleCommand publishArticleCommand = new PublishArticleCommand(id, date);
        articleCommandUseCase.publishArticle(publishArticleCommand);
    }

}
