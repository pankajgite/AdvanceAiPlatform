package com.codingshuttle.projects.lovable_clone.dto.suscription;

public record PlanLimitsResponse(
        String planName,
        Integer maxtokensPerDay,
        Integer maxProjects,
        Boolean unlimitedAi
) {
}
