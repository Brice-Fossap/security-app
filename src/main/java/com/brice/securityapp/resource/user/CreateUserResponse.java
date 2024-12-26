package com.brice.securityapp.resource.user;

import com.brice.securityapp.enums.Gender;
import com.brice.securityapp.enums.Role;

public record CreateUserResponse(
        Integer id,
        String firsName,
        String lastName,
        String email,
        Gender gender,
        Role role
) {
}
