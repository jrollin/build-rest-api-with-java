package com.talanlabs.training.bdd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class GetVersionStepDefinitionTest extends AbstractStepTest {

    private ResponseEntity<String> response = null;

    @When("the client calls GET {string}")
    public void theClientCallsGET(String uri) {

        Map<String, String> uriVariables = new HashMap<>();
        String url = buildUrl("/api/version", uriVariables);
        response = invokeRESTCall(url, HttpMethod.GET, null);
    }

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



