package com.talanlabs.training.bdd.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;


public class GetVersionStepDefinitionTest extends AbstractStepTest {


    @Then("the client receives status code of {int}")
    public void theClientReceivesStatusCodeOf(int statusCode) {
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            assertEquals(statusCode, response.getStatusCode().value());
        }
    }

    @And("the response contains version {string}")
    public void theResponseContainsVersion(String version) throws JsonProcessingException {
        if (response != null && response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            String readVersion = JsonPath.read(responseBody, "$.version").toString();
            assertEquals(readVersion, version);
        }
    }


}



