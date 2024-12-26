package com.brice.securityapp.service.person;

import com.brice.securityapp.exception.ResourceNotFoundException;
import com.brice.securityapp.mapper.PersonMapper;
import com.brice.securityapp.repository.PersonRepository;
import com.brice.securityapp.resource.person.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPerson {

    private final PersonRepository personRepository;

    public PersonResponse execute(Long id) {
        var person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        return PersonMapper.map(person);
    }
}
