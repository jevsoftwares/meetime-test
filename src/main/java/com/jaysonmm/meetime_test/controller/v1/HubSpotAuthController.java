package com.jaysonmm.meetime_test.controller.v1;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class HubSpotAuthController {
    
    @Value("${hubspot.client-id}")
    private String clientId;

    @Value("${hubspot.redirect-uri}")
    private String redirectUri;

    @Value("${hubspot.auth-uri}")
    private String authUri;

    private static final String SCOPES = "crm.objects.contacts.read";

    @GetMapping("/login")
    public ResponseEntity<Void> loginHubSpot(HttpServletResponse response) throws IOException {
        String authUrl = authUri + "?client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&scope=" + SCOPES +
                "&response_type=code";
        response.sendRedirect(authUrl);
        return ResponseEntity.ok().build();
    }
}
