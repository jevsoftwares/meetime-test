package com.jaysonmm.meetime_test.controller.v1;

import com.jaysonmm.meetime_test.service.HubSpotService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "HubSpot Integration",
        description = "Serviço responsável pela integração com HubSpot"))
public class HubSpotController {

    private final HubSpotService hubSpotService;

    @Operation(summary = "Listar contatos do HubSpot",
            description = "Busca contatos do HubSpot e retorna por json" )
    @ApiResponse(responseCode = "200")
    @ResponseBody
    @GetMapping("/contacts")
    public ResponseEntity<?> getContacts(@RequestHeader("Authorization") String authorization) {

        Map<String, Object> response = hubSpotService.getStringObjectMap(authorization);

        return ResponseEntity.ok(response);
    }

}
