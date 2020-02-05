package com.talanlabs.training.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
@Getter @NoArgsConstructor
public class Article {

    @Id
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
