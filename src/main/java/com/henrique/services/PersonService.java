package com.henrique.services;

import com.henrique.models.Person;
import com.henrique.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findOne(Integer id){
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Person with ID "+ id +" not found.")
        );
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person save(Person person){
        return personRepository.save(person);
    }

    public Person update(Person person){
        findOne(person.getId());
        return personRepository.save(person);
    }

    public void delete(Integer id){
        findOne(id);
        personRepository.deleteById(id);
    }
}
