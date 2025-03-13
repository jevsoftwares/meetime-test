package com.jaysonmm.meetime_test.controller.v1;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@RestController
@RequestMapping("/hubspot")
public class HubSpotController {

    private final WebClient webClient;

    public HubSpotController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    private static final String API_URL = "https://api.hubapi.com/crm/v3/objects/contacts";

    @GetMapping("/contacts")
    public ResponseEntity<?> getContacts(@RequestHeader("Authorization") String authorization) {
        Map<String, Object> response = webClient.get()
                .uri(API_URL)
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        return ResponseEntity.ok(response);
    }
}
