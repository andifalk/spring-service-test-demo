package com.example.servicetestdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = PersonServiceIntegrationTest.PersonServiceConfig.class,
        properties = {"logging.level.org.springframework.orm.jpa.JpaTransactionManager=debug"})
@Transactional
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonService cut;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void verifySavePerson() {
        Person person = new Person(UUID.randomUUID(), "Hans", "Mustermann");
        Person persistedPerson = cut.save(person);
        assertThat(persistedPerson).isNotNull();
    }

    @Test
    public void verifyFindPerson() {
        UUID personIdentifier = UUID.randomUUID();
        Person person = new Person(personIdentifier, "Hans", "Mustermann");
        cut.save(person);

        Person personFound = cut.findOneByIdentifier(personIdentifier);
        assertThat(personFound).isNotNull();
    }

    @Test
    public void verifyFindAllPersons() {
        UUID personIdentifier = UUID.randomUUID();
        Person person = new Person(personIdentifier, "Hans", "Mustermann");
        Person persistedPerson = cut.save(person);

        List<Person> personList = cut.findAll();
        assertThat(personList).isNotNull().contains(persistedPerson);
    }

    @Configuration
    @ComponentScan
    static class PersonServiceConfig {
    }

}
