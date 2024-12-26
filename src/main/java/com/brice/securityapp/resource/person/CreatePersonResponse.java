package com.brice.securityapp.resource.person;

public record CreatePersonResponse(
        Long id,
        String firstName,
        String lastName
) {
}
