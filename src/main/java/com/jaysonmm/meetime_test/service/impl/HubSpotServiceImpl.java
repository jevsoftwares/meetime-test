package com.jaysonmm.meetime_test.service.impl;

import com.google.gson.Gson;
import com.jaysonmm.meetime_test.controller.exception.exception.CustomUnauthorizedException;
import com.jaysonmm.meetime_test.controller.request.ContactRequest;
import com.jaysonmm.meetime_test.service.HubSpotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class HubSpotServiceImpl implements HubSpotService {

    private final WebClient webClient = WebClient.builder().build();
    private static final String API_URL = "https://api.hubapi.com/crm/v3/objects/contacts";

    @Override
    public Map<String, Object> listContacts(String authorization) {
        return webClient.get()
                .uri(API_URL)
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }

    @Override
    public void createContact(ContactRequest contactRequest, String authorization) {


        Map<String, Object> contactData = Map.of(
                "properties", Map.of(
                        "email", contactRequest.getEmail(),
                        "firstname", contactRequest.getFirstName(),
                        "lastname", contactRequest.getLastName()
                )
        );

        log.info("Payload enviado: {}", new Gson().toJson(contactData));
        try {
            Map<String, Object> objectMap = webClient.post()
                    .uri(API_URL)
                    .header(HttpHeaders.AUTHORIZATION, authorization)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .bodyValue(contactData)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    })
                    .block();
            assert objectMap != null;
            log.info(new Gson().toJson(objectMap.values()));
        } catch (WebClientResponseException.Unauthorized e){
            throw new CustomUnauthorizedException("Acesso não autorizado");
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Erro ao criar contato no HubSpot");
        }
    }
}
