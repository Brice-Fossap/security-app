package com.brice.securityapp.resource.user;

public record LoginUserRequest(
        String email,
        String password
) {
}
