package com.brice.securityapp.service.person;

import com.brice.securityapp.entity.Person;
import com.brice.securityapp.mapper.CreatePersonMapper;
import com.brice.securityapp.repository.PersonRepository;
import com.brice.securityapp.resource.person.CreatePersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllPerson {

    private final PersonRepository personRepository;

    public List<CreatePersonResponse> execute() {
        List<Person> personList = personRepository.findAll();

        return personList.stream()
                .map(CreatePersonMapper::map)
                .collect(Collectors.toList());
    }
}
