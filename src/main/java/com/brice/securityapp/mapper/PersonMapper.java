package com.brice.securityapp.mapper;

import com.brice.securityapp.entity.Person;
import com.brice.securityapp.resource.person.PersonRequest;
import com.brice.securityapp.resource.person.PersonResponse;

public class PersonMapper {

    public PersonMapper() {
        // noop
    }

    public static Person map(PersonRequest request) {
        return Person.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
    }

    public static PersonResponse map(Person person) {
        return new PersonResponse(
                person.getId(),
                person.getFirstName(),
                person.getLastName()
        );
    }
}
