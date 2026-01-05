package com.codingshuttle.projects.lovable_clone.service;

import com.codingshuttle.projects.lovable_clone.dto.suscription.CheckoutRequest;
import com.codingshuttle.projects.lovable_clone.dto.suscription.CheckoutResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.PortalResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse createCustomerPortal(Long userId);
}
