package com.brice.securityapp.mapper;

import com.brice.securityapp.entity.User;
import com.brice.securityapp.resource.user.CreateUserRequest;
import com.brice.securityapp.resource.user.CreateUserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreateUserMapper {

    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CreateUserMapper() {
        // noop
    }

    public static User map(CreateUserRequest request) {
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .gender(request.gender())
                .role(request.role())
                .build();
    }

    public static CreateUserResponse map(User user) {
        return new CreateUserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGender(),
                user.getRole()
        );
    }
}
