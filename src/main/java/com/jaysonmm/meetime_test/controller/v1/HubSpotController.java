package com.jaysonmm.meetime_test.controller.v1;

import com.jaysonmm.meetime_test.controller.request.ContactRequest;
import com.jaysonmm.meetime_test.service.HubSpotService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.jaysonmm.meetime_test.utlis.Constants.AUTHENTICATION_HEADER;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "HubSpot Integration",
        description = "Serviço responsável pela integração com HubSpot"))

@SecurityRequirement(name = AUTHENTICATION_HEADER)
public class HubSpotController {

    private final HubSpotService hubSpotService;

    @Operation(summary = "Listar contatos do HubSpot",
            description = "Busca contatos do HubSpot e retorna por json" )
    @ApiResponse(responseCode = "200")
    @ResponseBody
    @GetMapping("/contacts")
    public ResponseEntity<?> listContacts(@RequestHeader("Authorization") @Parameter(hidden = true) String authorization) {

        Map<String, Object> response = hubSpotService.listContacts(authorization);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cadastrar contato no HubSpot",
            description = "Cadastra contatos no HubSpot" )
    @ApiResponse(responseCode = "201")
    @ResponseBody
    @PostMapping("/contact")
    public ResponseEntity<Void> createContact(@RequestBody @Valid ContactRequest contactRequest,
                                              @Parameter(hidden = true) @RequestHeader("Authorization") String authorization) {

        hubSpotService.createContact(contactRequest, authorization);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
