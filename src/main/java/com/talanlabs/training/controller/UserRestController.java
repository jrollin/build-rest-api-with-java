package com.talanlabs.training.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRestController {

    @GetMapping("/api/user")
    public Map<String, String> user(@AuthenticationPrincipal UserDetails principal) {
        Map<String, String> infos = new HashMap<>();

        infos.put("username", principal.getUsername());
        infos.put("password", principal.getPassword());
        infos.put("roles", principal.getAuthorities().toString());

        return infos;
    }
}
