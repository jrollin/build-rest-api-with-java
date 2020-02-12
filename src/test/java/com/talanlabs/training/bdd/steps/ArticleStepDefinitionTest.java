package com.talanlabs.training.bdd.steps;

import io.cucumber.java.en.And;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleStepDefinitionTest extends AbstractStepTest {

    @And("the response contains {string}")
    public void theResponseContains(String response) {
        assertThat(response).isEqualTo("[]");
    }
}
