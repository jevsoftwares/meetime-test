package com.jaysonmm.meetime_test.controller.v1;

import com.jaysonmm.meetime_test.controller.exception.exception.CustomUnauthorizedException;
import com.jaysonmm.meetime_test.service.HubSpotAuthService;
import com.jaysonmm.meetime_test.utlis.Constants;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class HubSpotAuthController {
    
    private final HubSpotAuthService hubSpotAuthService;

    @Value("${config.apikey}")
    private String xApiKeyServer;

    @Operation(summary = "Realizar login na plataforma HubSpot",
            description = "Direciona para a plataforma HubSpot para login",
            externalDocs = @ExternalDocumentation(
                    description = "Essa url deve ser acessada somente pelo navegador para retorno do callback",
                    url = "http://localhost:8999/hubspot/v1/auth/login"))
    @ApiResponse(responseCode = "200")
    @GetMapping("/login")
    public ResponseEntity<Void> loginHubSpot(@Parameter(hidden = true) @RequestParam(Constants.API_KEY_HEADER) String xApiKey, HttpServletResponse response) throws IOException {
        if (Objects.isNull(xApiKey) || !Objects.equals(xApiKey, xApiKeyServer)){
            throw new CustomUnauthorizedException("Não autorizado");
        }
        hubSpotAuthService.loginHubSpot(response);
        return ResponseEntity.ok().build();
    }
}
