package com.brice.securityapp.service.person;

import com.brice.securityapp.mapper.CreatePersonMapper;
import com.brice.securityapp.repository.PersonRepository;
import com.brice.securityapp.resource.person.CreatePersonRequest;
import com.brice.securityapp.resource.person.CreatePersonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePerson {

    private final PersonRepository personRepository;

    public CreatePersonResponse execute(CreatePersonRequest request) {
        log.info("Request given with information : {}", request);

        var person = CreatePersonMapper.map(request);
        person = personRepository.save(person);

        log.info("Person saved : {}", person);
        return CreatePersonMapper.map(person);
    }
}
