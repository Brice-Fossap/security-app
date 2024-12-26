package com.brice.securityapp.mapper;

import com.brice.securityapp.entity.User;
import com.brice.securityapp.resource.user.CreateUserRequest;
import com.brice.securityapp.resource.user.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserMapper() {
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

    public static UserResponse map(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getGender(),
                user.getRole()
        );
    }
}
