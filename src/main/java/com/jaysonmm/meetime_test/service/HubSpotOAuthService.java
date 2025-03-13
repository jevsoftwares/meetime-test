package com.jaysonmm.meetime_test.service;

import com.jaysonmm.meetime_test.controller.response.HubSpotOAuthResponse;

public interface HubSpotOAuthService {
    HubSpotOAuthResponse callback(String code);
}
