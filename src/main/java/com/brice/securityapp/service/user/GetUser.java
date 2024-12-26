package com.brice.securityapp.service.user;

import com.brice.securityapp.exception.ResourceNotFoundException;
import com.brice.securityapp.mapper.UserMapper;
import com.brice.securityapp.repository.UserRepository;
import com.brice.securityapp.resource.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUser {

    private final UserRepository userRepository;

    public UserResponse execute(Integer id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserMapper.map(user);
    }
}
