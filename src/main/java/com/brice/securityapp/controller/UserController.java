package com.brice.securityapp.controller;

import com.brice.securityapp.resource.user.CreateUserRequest;
import com.brice.securityapp.resource.user.CreateUserResponse;
import com.brice.securityapp.service.user.CreateUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUser createUser;

    public UserController(CreateUser createUser) {
        this.createUser = createUser;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        var response = createUser.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
