package com.jaysonmm.meetime_test.service.impl;

import com.jaysonmm.meetime_test.controller.response.HubSpotOAuthResponse;
import com.jaysonmm.meetime_test.service.HubSpotOAuthService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.AccessDeniedException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubSpotOAuthServiceImpl implements HubSpotOAuthService {

    @Value("${hubspot.client-id}")
    private String clientId;

    @Value("${hubspot.client-secret}")
    private String clientSecret;

    @Value("${hubspot.redirect-uri}")
    private String redirectUri;

    @Value("${hubspot.token-uri}")
    private String tokenUri;

    private final WebClient webClient = WebClient.builder().build();

    @SneakyThrows
    @Override
    public HubSpotOAuthResponse callback(String code) {
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
            return HubSpotOAuthResponse.builder().token(accessToken).build();
        } else {
            throw new AccessDeniedException("Acesso não autorizado");
        }
    }
}
