package com.example.servicetestdemo.person;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;
    private final IdGenerator idGenerator;

    public PersonService(PersonRepository personRepository, IdGenerator idGenerator) {
        this.personRepository = personRepository;
        this.idGenerator = idGenerator;
    }

    public Person findOneByIdentifier(UUID identifier) {
        return personRepository.findOneByIdentifier(identifier);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public Person save(Person entity) {
        if (entity.getIdentifier() == null) {
            entity.setIdentifier(idGenerator.generateId());
        }
        return personRepository.save(entity);
    }
}
