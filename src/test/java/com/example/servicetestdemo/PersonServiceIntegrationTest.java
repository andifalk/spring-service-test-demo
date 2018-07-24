package com.example.servicetestdemo;

import com.example.servicetestdemo.person.Person;
import com.example.servicetestdemo.person.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;
import org.springframework.util.JdkIdGenerator;

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
    @EnableJpaRepositories(basePackages = PersonServiceConfig.BASE_PACKAGE_PATH)
    @EntityScan(basePackages = PersonServiceConfig.BASE_PACKAGE_PATH)
    @AutoConfigureDataJpa
    @ComponentScan(basePackages = PersonServiceConfig.BASE_PACKAGE_PATH)
    static class PersonServiceConfig {

        static final String BASE_PACKAGE_PATH = "com.example.servicetestdemo.person";

        @Bean
        IdGenerator idGenerator() {
            return new JdkIdGenerator();
        }

    }

}
