package com.talanlabs.training.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"version=1.4"})
public class GetVersionControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    ObjectMapper objectMapper;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void getVersion() throws Exception {

        Map<String, Float> map = new HashMap<>();
        map.put("version", (float) 1.4);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/version"), HttpMethod.GET, entity, String.class);
        String actual = response.getBody();

        assertThat(actual).isEqualTo(objectMapper.writeValueAsString(map));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
