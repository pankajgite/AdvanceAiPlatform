package com.codingshuttle.projects.lovable_clone.service.impl;

import com.codingshuttle.projects.lovable_clone.dto.suscription.CheckoutRequest;
import com.codingshuttle.projects.lovable_clone.dto.suscription.CheckoutResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.PortalResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.SubscriptionResponse;
import com.codingshuttle.projects.lovable_clone.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse createCustomerPortal(Long userId) {
        return null;
    }
}
