package com.brice.securityapp.controller;

import com.brice.securityapp.resource.user.CreateUserRequest;
import com.brice.securityapp.resource.user.UpdateUserRequest;
import com.brice.securityapp.resource.user.UserResponse;
import com.brice.securityapp.service.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final CreateUser createUser;
    private final GetUser getUser;
    private final GetAllUser getAllUser;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        var response = createUser.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Integer id) {
        var response = getUser.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<UserResponse> responseList = getAllUser.execute();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Integer id,
                                                   @Valid @RequestBody UpdateUserRequest request) {
        var response = updateUser.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Integer id) {
        deleteUser.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
