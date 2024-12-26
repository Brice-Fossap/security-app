package com.brice.securityapp.resource.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginUserRequest(
        @NotNull
        @Email
        String email,

        @NotNull
        String password
) {
}
