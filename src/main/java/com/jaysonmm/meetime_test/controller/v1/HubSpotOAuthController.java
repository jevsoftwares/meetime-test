package com.jaysonmm.meetime_test.controller.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class HubSpotOAuthController {

    @Value("${hubspot.client-id}")
    private String clientId;

    @Value("${hubspot.client-secret}")
    private String clientSecret;

    @Value("${hubspot.redirect-uri}")
    private String redirectUri;

    @Value("${hubspot.token-uri}")
    private String tokenUri;

    private final WebClient webClient;

    public HubSpotOAuthController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("redirect_uri", redirectUri);
        formData.add("code", code);

        Map<String, Object> response = webClient.post()
                .uri(tokenUri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        if (response != null && response.containsKey("access_token")) {
            String accessToken = (String) response.get("access_token");
            return ResponseEntity.ok("Access Token: " + accessToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao obter token");
        }
    }
}
