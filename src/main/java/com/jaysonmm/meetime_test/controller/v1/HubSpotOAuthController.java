package com.jaysonmm.meetime_test.controller.v1;

import com.jaysonmm.meetime_test.controller.response.HubSpotOAuthResponse;
import com.jaysonmm.meetime_test.service.HubSpotOAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class HubSpotOAuthController {

    private final HubSpotOAuthService hubSpotOAuthService;

    @Operation(summary = "Recupera acesso em callback",
            description = "Recupera acesso em callback e retorna o token. Endpoint ocultada na documentação para acesso somente em callback",
            hidden = true)
    @ApiResponse(responseCode = "200")
    @GetMapping("/callback")
    public ResponseEntity<HubSpotOAuthResponse> callback(@RequestParam("code") String code) {
        HubSpotOAuthResponse hubSpotOAuthResponse = hubSpotOAuthService.callback(code);
        return ResponseEntity.ok().body(hubSpotOAuthResponse);
    }
}
