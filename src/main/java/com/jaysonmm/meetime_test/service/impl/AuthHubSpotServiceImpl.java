package com.jaysonmm.meetime_test.service.impl;

import com.jaysonmm.meetime_test.service.AuthHubSpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthHubSpotServiceImpl implements AuthHubSpotService {

    @Value("${hubspot.url}")
    private String hubSpotUrl;

    @Value("${hubspot.client-id}")
    private String clientId;

    @Value("${hubspot.client-secret}")
    private String clientSecret;

    @Value("${hubspot.redirect-uri}")
    private String redirectUri;

    private final WebClient webClient = WebClient.builder().baseUrl(hubSpotUrl).build();

    @Override
    public String getAuthorizationUrl() {
        return hubSpotUrl + "/oauth/authorize?" +
                "client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&scope=oauth";
    }

    public Mono<Map<String, Object>> exchangeToken(String code) {
        return webClient.post()
                .uri("/oauth/v1/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue("grant_type=authorization_code" +
                        "&client_id=" + clientId +
                        "&client_secret=" + clientSecret +
                        "&redirect_uri=" + redirectUri +
                        "&code=" + code)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
}
