package com.codingshuttle.projects.lovable_clone.service;

import com.codingshuttle.projects.lovable_clone.dto.suscription.CheckoutRequest;
import com.codingshuttle.projects.lovable_clone.dto.suscription.CheckoutResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.PortalResponse;

import java.util.Map;

public interface PaymentProcessor {
    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request);

    PortalResponse openCustomerPortal();

    void handleWebhookEvent(String type, Map<String, String> metadata);
}
