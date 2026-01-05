package com.codingshuttle.projects.lovable_clone.dto.auth;

import lombok.Data;


public record AuthResponse(
        String token,
        UserProfileResponse userProfileResponse) {

}
