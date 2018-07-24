package com.example.servicetestdemo.person;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/persons")
public class PersonRestController {

    private final PersonService personService;

    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping(path = "/{personId}")
    public ResponseEntity<Person> getPerson(@PathVariable("personId") UUID personId) {
        Person person = personService.findOneByIdentifier(personId);
        if (person == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(person);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        return personService.save(person);
    }

}
