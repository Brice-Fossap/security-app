package com.brice.securityapp.service.person;

import com.brice.securityapp.mapper.CreatePersonMapper;
import com.brice.securityapp.repository.PersonRepository;
import com.brice.securityapp.resource.person.CreatePersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerson {

    private final PersonRepository personRepository;

    public CreatePersonResponse execute (Long id) {
        var person = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        return CreatePersonMapper.map(person);
    }
}
