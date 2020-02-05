package com.talanlabs.training.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class Article {

    private Long id;
    private String title;
    private String author;
    private int version;

    public Article(Long id, String title, String author, int version) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.version = version;
    }
}
