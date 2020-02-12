package com.talanlabs.training.bdd.steps;

import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class CommonStepDefinitionTest extends AbstractStepTest {

    @When("the client calls GET {string}")
    public void theClientCallsGET(String uri) {

        Map<String, String> uriVariables = new HashMap<>();
        String url = buildUrl("/api/version", uriVariables);
        response = invokeRESTCall(url, HttpMethod.GET, null);
    }
}
