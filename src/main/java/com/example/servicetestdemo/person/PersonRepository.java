package com.example.servicetestdemo.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findOneByIdentifier(UUID identifier);

}
