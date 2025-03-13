package com.jaysonmm.meetime_test.service.impl;

import com.jaysonmm.meetime_test.service.HubSpotAuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class HubSpotAuthServiceImpl implements HubSpotAuthService {

    @Value("${hubspot.client-id}")
    private String clientId;

    @Value("${hubspot.client-secret}")
    private String clientSecret;

    @Value("${hubspot.redirect-uri}")
    private String redirectUri;

    @Value("${hubspot.auth-uri}")
    private String authUri;

    private static final String SCOPES = "crm.objects.contacts.read";


    @Override
    public void loginHubSpot(HttpServletResponse response) {
        String authUrl = authUri + "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&scope=" + SCOPES +
                "&response_type=code";
        try {
            response.sendRedirect(authUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
