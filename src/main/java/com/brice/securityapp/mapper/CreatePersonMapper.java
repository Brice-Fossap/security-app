package com.brice.securityapp.mapper;

import com.brice.securityapp.entity.Person;
import com.brice.securityapp.resource.person.CreatePersonRequest;
import com.brice.securityapp.resource.person.CreatePersonResponse;

public class CreatePersonMapper {

    public CreatePersonMapper() {
        // noop
    }

    public static Person map(CreatePersonRequest request) {
        return Person.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();
    }

    public static CreatePersonResponse map(Person person) {
        return new CreatePersonResponse(
                person.getId(),
                person.getFirstName(),
                person.getLastName()
        );
    }
}
