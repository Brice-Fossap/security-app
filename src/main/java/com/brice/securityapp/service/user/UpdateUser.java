package com.brice.securityapp.service.user;

import com.brice.securityapp.entity.User;
import com.brice.securityapp.exception.ResourceNotFoundException;
import com.brice.securityapp.mapper.UserMapper;
import com.brice.securityapp.repository.UserRepository;
import com.brice.securityapp.resource.user.UpdateUserRequest;
import com.brice.securityapp.resource.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUser {

    private final UserRepository userRepository;

    public UserResponse execute(Integer id, UpdateUserRequest request) {
        var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        updateUser(user, request);

        var userUpdated = userRepository.save(user);
        return UserMapper.map(userUpdated);
    }

    private void updateUser(User user, UpdateUserRequest request) {
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setGender(request.gender());
        user.setRole(request.role());
    }
}
