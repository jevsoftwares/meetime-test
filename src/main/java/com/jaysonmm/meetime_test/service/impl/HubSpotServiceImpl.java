package com.jaysonmm.meetime_test.service.impl;

import com.jaysonmm.meetime_test.service.HubSpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubSpotServiceImpl implements HubSpotService {

    private final WebClient webClient = WebClient.builder().build();
    private static final String API_URL = "https://api.hubapi.com/crm/v3/objects/contacts";

    @Override
    public Map<String, Object> getStringObjectMap(String authorization) {
        Map<String, Object> response = webClient.get()
                .uri(API_URL)
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
        return response;
    }
}
