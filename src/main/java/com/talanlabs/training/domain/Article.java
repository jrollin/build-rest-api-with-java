package com.talanlabs.training.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
