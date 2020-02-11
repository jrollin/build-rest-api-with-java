package com.talanlabs.training.application.command;


import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PublishArticleCommand {

    private Long id;

    private LocalDate date;

    public PublishArticleCommand(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
}

