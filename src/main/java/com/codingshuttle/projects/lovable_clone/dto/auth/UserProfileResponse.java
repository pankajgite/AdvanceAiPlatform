package com.codingshuttle.projects.lovable_clone.dto.auth;

import com.codingshuttle.projects.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record UserProfileResponse(
        Long id,
        String username,
        String name,
        ProjectRole role,
        Instant invitedAt
) {
}
