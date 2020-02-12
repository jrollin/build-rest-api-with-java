package com.talanlabs.training.bdd;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.talanlabs.training.TrainingApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@SpringBootTest(classes = TrainingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@DirtiesContext
public abstract class AbstractStepTest {

    @Autowired(required = false)
    private TestRestTemplate restTemplate;

    protected ObjectMapper mapper = new ObjectMapper();

    protected  final String host = "localhost";

    @LocalServerPort
    protected int port;


    public TestRestTemplate getRestTemplate() {

        return restTemplate != null ? restTemplate : new TestRestTemplate();
    }

    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> invokeRESTCall(String url, HttpMethod method, HttpEntity<?> requestEntity) {

        return getRestTemplate().exchange(url, method, requestEntity, String.class);
    }

    public HttpHeaders getDefaultHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public String buildUrl(String host, int port, String path, Map<String, String> uriVariables,
                           MultiValueMap<String, String> queryParams) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path).host(host).port(port).scheme("http");
        if (queryParams != null && !queryParams.isEmpty())
            builder.queryParams(queryParams);
        UriComponents uriComponent = uriVariables != null && !uriVariables.isEmpty()
                ? builder.buildAndExpand(uriVariables)
                : builder.build();

        return uriComponent.toUri().toString();
    }

    public String buildUrl(String host, int port, String path) {

        return buildUrl(host, port, path, null, null);
    }

    public String buildUrl(String host, int port, String path, Map<String, String> uriVariables) {

        return buildUrl(host, port, path, uriVariables, null);
    }

    public String buildUrl(String path, Map<String, String> uriVariables) {

        return buildUrl(host, port, path, uriVariables);
    }

}

