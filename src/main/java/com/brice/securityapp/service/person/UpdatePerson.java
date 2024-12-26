package com.brice.securityapp.service.person;

import com.brice.securityapp.entity.Person;
import com.brice.securityapp.exception.ResourceNotFoundException;
import com.brice.securityapp.mapper.PersonMapper;
import com.brice.securityapp.repository.PersonRepository;
import com.brice.securityapp.resource.person.PersonRequest;
import com.brice.securityapp.resource.person.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePerson {

    private final PersonRepository personRepository;

    public PersonResponse execute(Long id, PersonRequest request) {
        var person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        updatePerson(person, request);

        var personUpdated = personRepository.save(person);
        return PersonMapper.map(personUpdated);
    }

    private void updatePerson(Person person, PersonRequest request) {
        person.setFirstName(request.firstName());
        person.setLastName(request.lastName());
    }
}
