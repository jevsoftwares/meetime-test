package com.jaysonmm.meetime_test.controller.v1;

import com.jaysonmm.meetime_test.controller.exception.exception.CustomUnauthorizedException;
import com.jaysonmm.meetime_test.service.WebhookService;
import com.jaysonmm.meetime_test.utlis.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("/v1/webhook")
@RequiredArgsConstructor
public class WebhookController {
    private final WebhookService webhookService;

    @Value("${config.apikey}")
    private String xApiKeyServer;

    @Operation(summary = "Ouvir chamadas de criação de contato do HubSpot",
            description = "Ouve as chamadas de criação de contato do HubSpot")
    @ApiResponse(responseCode = "200")
    @PostMapping("/object-creation")
    public ResponseEntity<Void> objectCreation(@RequestParam(Constants.API_KEY_HEADER) String xApiKey, @RequestBody Object[] payload) throws IOException {
        if (Objects.isNull(xApiKey) || !Objects.equals(xApiKey, xApiKeyServer)){
            throw new CustomUnauthorizedException("Não autorizado");
        }
        webhookService.objectCreation(payload);
        return ResponseEntity.ok().build();
    }
}
