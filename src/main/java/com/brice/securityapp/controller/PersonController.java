package com.brice.securityapp.controller;

import com.brice.securityapp.resource.person.CreatePersonRequest;
import com.brice.securityapp.resource.person.CreatePersonResponse;
import com.brice.securityapp.service.person.CreatePerson;
import com.brice.securityapp.service.person.GetAllPerson;
import com.brice.securityapp.service.person.GetPerson;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final CreatePerson createPerson;
    private final GetPerson getPerson;
    private final GetAllPerson getAllPerson;

    public PersonController(CreatePerson createPerson, GetPerson getPerson, GetAllPerson getAllPerson) {
        this.createPerson = createPerson;
        this.getPerson = getPerson;
        this.getAllPerson = getAllPerson;
    }

    @PostMapping
    public ResponseEntity<CreatePersonResponse> createPerson(@Valid @RequestBody CreatePersonRequest request) {
        var response = createPerson.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatePersonResponse> getPerson(@PathVariable("id") Long id) {
        var response = getPerson.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CreatePersonResponse>> getAllPerson() {
        List<CreatePersonResponse> responseList = getAllPerson.execute();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
