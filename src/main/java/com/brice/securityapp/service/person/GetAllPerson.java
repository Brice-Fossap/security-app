package com.brice.securityapp.service.person;

import com.brice.securityapp.entity.Person;
import com.brice.securityapp.mapper.PersonMapper;
import com.brice.securityapp.repository.PersonRepository;
import com.brice.securityapp.resource.person.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllPerson {

    private final PersonRepository personRepository;

    public List<PersonResponse> execute() {
        List<Person> personList = personRepository.findAll();

        return personList.stream()
                .map(PersonMapper::map)
                .collect(Collectors.toList());
    }
}
