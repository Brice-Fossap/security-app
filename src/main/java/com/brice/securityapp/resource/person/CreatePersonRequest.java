package com.brice.securityapp.resource.person;

import jakarta.validation.constraints.NotBlank;

public record CreatePersonRequest(
        Long id,
        @NotBlank
        String firstName,
        String lastName
) {
}
