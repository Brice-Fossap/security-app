package com.brice.securityapp.service.person;

import com.brice.securityapp.mapper.PersonMapper;
import com.brice.securityapp.repository.PersonRepository;
import com.brice.securityapp.resource.person.PersonRequest;
import com.brice.securityapp.resource.person.PersonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePerson {

    private final PersonRepository personRepository;

    public PersonResponse execute(PersonRequest request) {
        log.info("Request given with information : {}", request);

        var person = PersonMapper.map(request);
        person = personRepository.save(person);

        log.info("Person saved : {}", person);
        return PersonMapper.map(person);
    }
}
