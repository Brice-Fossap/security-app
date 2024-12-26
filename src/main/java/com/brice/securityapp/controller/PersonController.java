package com.brice.securityapp.controller;

import com.brice.securityapp.resource.person.PersonRequest;
import com.brice.securityapp.resource.person.PersonResponse;
import com.brice.securityapp.service.person.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {

    private final CreatePerson createPerson;
    private final GetPerson getPerson;
    private final GetAllPerson getAllPerson;
    private final UpdatePerson updatePerson;
    private final DeletePerson deletePerson;

    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody PersonRequest request) {
        var response = createPerson.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPerson(@PathVariable("id") Long id) {
        var response = getPerson.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAllPerson() {
        List<PersonResponse> responseList = getAllPerson.execute();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable("id") Long id,
                                                       @Valid @RequestBody PersonRequest request) {
        var response = updatePerson.execute(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long id) {
        deletePerson.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
