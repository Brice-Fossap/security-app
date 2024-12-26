package com.brice.securityapp.service.user;

import com.brice.securityapp.entity.User;
import com.brice.securityapp.mapper.UserMapper;
import com.brice.securityapp.repository.UserRepository;
import com.brice.securityapp.resource.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllUser {

    private final UserRepository userRepository;

    public List<UserResponse> execute() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(UserMapper::map)
                .collect(Collectors.toList());
    }
}
