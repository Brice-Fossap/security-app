package com.brice.securityapp.resource.user;

import com.brice.securityapp.enums.Gender;
import com.brice.securityapp.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank @Size(max = 25)
        String firstName,
        @Size(max = 25)
        String lastName,
        @Email @NotBlank
        String email,
        @NotBlank @Size(max = 25, min = 8)
        String password,
        @NotNull @Enumerated(EnumType.STRING)
        Gender gender,
        @NotNull @Enumerated(EnumType.STRING)
        Role role
) {
}
