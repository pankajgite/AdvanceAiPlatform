package com.codingshuttle.projects.lovable_clone.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest (
        @NotBlank @Email String email,
        @Size(min = 1, max = 30) String password,
        @Size(min = 4) String name
){
}
