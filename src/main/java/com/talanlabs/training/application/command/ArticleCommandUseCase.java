package com.talanlabs.training.application.command;

public interface ArticleCommandUseCase {

    void submitArticle(SubmitArticleCommand submitArticleCommand);

    void publishArticle(PublishArticleCommand publishArticleCommand);
}
