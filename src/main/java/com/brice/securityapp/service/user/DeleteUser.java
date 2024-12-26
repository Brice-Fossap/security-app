package com.brice.securityapp.service.user;

import com.brice.securityapp.exception.ResourceNotFoundException;
import com.brice.securityapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUser {

    private final UserRepository userRepository;

    public void execute(Integer id) {
        var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }
}
