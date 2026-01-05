package com.codingshuttle.projects.lovable_clone.service.impl;

import com.codingshuttle.projects.lovable_clone.dto.suscription.PlanLimitsResponse;
import com.codingshuttle.projects.lovable_clone.dto.suscription.UsageTodayResponse;
import com.codingshuttle.projects.lovable_clone.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {
    @Override
    public UsageTodayResponse getUsageToday(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsofUser(Long userId) {
        return null;
    }
}
