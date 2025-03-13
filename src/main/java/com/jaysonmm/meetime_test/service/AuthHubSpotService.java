package com.jaysonmm.meetime_test.service;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthHubSpotService {

    String getAuthorizationUrl();

    Mono<Map<String, Object>> exchangeToken(String code);
}
