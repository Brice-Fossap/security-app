package com.brice.securityapp.resource.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ForgotRequest(
        @NotNull
        @Email
        String email
) {
}
