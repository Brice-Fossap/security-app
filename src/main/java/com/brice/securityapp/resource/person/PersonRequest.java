package com.brice.securityapp.resource.person;

import jakarta.validation.constraints.NotBlank;

public record PersonRequest(
        @NotBlank
        String firstName,
        String lastName
) {
}
