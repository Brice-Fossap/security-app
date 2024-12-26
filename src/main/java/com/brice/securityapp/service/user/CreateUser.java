package com.brice.securityapp.service.user;

import com.brice.securityapp.mapper.CreateUserMapper;
import com.brice.securityapp.repository.UserRepository;
import com.brice.securityapp.resource.user.CreateUserRequest;
import com.brice.securityapp.resource.user.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUser {

    private final UserRepository userRepository;

    public CreateUserResponse execute(CreateUserRequest request) {
        log.info("Create user with information: {}", request);

        var user = CreateUserMapper.map(request);
        user = userRepository.save(user);

        log.info("User saved : {}", user);
        return CreateUserMapper.map(user);
    }
}
