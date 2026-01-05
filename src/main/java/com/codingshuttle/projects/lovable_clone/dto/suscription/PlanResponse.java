package com.codingshuttle.projects.lovable_clone.dto.suscription;

public record PlanResponse(
        String name,
        String stripePriceId,
        Integer maxProjects,
        Integer maxTokensPerDay,
        Integer maxPreviews,//max number of previews allowed per plan
        Boolean unlimitedAi,
        String price
) {
}
