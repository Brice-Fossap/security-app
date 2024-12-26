package com.brice.securityapp.service.person;

import com.brice.securityapp.exception.ResourceNotFoundException;
import com.brice.securityapp.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePerson {

    private final PersonRepository personRepository;

    public void execute(Long id) {
        var person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not found"));
        personRepository.delete(person);
    }
}
