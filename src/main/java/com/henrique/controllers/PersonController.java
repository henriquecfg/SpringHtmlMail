package com.henrique.controllers;


import com.henrique.models.Person;
import com.henrique.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{person.id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Person> get(@PathVariable("person.id") Integer personId) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findOne(personId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Person> create(@Validated @RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(person));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Person> update(@Validated @RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.update(person));
    }

    @DeleteMapping("/{person.id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("person.id") Integer personId) {
        personService.delete(personId);
    }
}


