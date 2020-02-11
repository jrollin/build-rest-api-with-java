package com.talanlabs.training.application.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode
@Getter
public class SubmitArticleCommand {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotBlank(message = "Author cannot be empty")
    private String author;

    public SubmitArticleCommand(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
