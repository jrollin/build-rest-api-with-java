package com.talanlabs.training.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class VersionRestController {

    @Value("${version}")
    private Float version;

    @GetMapping("/api/version")
    public HashMap<String, Float> getVersion() {
        HashMap<String, Float> response = new HashMap<String, Float>();
        response.put("version", version);
        return response;
    }
}
