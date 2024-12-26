package com.brice.securityapp.resource.user;

import com.brice.securityapp.enums.Gender;
import com.brice.securityapp.enums.Role;

public record UserResponse(
        Integer id,
        String firstName,
        String lastName,
        String email,
        Gender gender,
        Role role
) {
}
