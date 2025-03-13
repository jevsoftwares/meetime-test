package com.jaysonmm.meetime_test.service.impl;

import com.jaysonmm.meetime_test.service.WebhookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhookServiceImpl implements WebhookService {

    @Override
    public void objectCreation(Object[] object) {
        log.warn("Webhook acionado com os seguintes dados: {}", object);
    }
}
