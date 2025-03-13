package com.jaysonmm.meetime_test.controller.v1;

import com.jaysonmm.meetime_test.service.HubSpotAuthService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class HubSpotAuthController {
    
    private final HubSpotAuthService hubSpotAuthService;

    @Operation(summary = "Realizar login na plataforma HubSpot",
            description = "Direciona para a plataforma HubSpot para login",
            externalDocs = @ExternalDocumentation(
                    description = "Essa url deve ser acessada somente pelo navegador para retorno do callback",
                    url = "https://localhost:8999/hubspot/v1/auth/login"))
    @ApiResponse(responseCode = "200")
    @GetMapping("/login")
    public ResponseEntity<Void> loginHubSpot(HttpServletResponse response) throws IOException {
        hubSpotAuthService.loginHubSpot(response);
        return ResponseEntity.ok().build();
    }
}
