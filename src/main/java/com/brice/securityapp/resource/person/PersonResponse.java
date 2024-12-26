package com.brice.securityapp.resource.person;

public record PersonResponse(
        Long id,
        String firstName,
        String lastName
) {
}
