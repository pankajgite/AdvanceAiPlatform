package com.codingshuttle.projects.lovable_clone.service;

import com.codingshuttle.projects.lovable_clone.dto.suscription.PlanLimitsResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
    UsageTodayResponse getUsageToday(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsofUser(Long userId);
}
