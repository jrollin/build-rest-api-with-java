package com.talanlabs.training.application.presentation;

import com.talanlabs.training.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String author;

    public ArticleDto(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public static ArticleDto fromDomain(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getAuthor());
    }
}
