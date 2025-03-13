package com.jaysonmm.meetime_test.service;

import com.jaysonmm.meetime_test.controller.request.ContactRequest;

import java.util.Map;

public interface HubSpotService {
    Map<String, Object> listContacts(String authorization);
    void createContact(ContactRequest contactRequest, String authorization);
}
