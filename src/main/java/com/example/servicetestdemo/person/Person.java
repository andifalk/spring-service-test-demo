package com.example.servicetestdemo.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Person extends AbstractPersistable<Long> {

    @NotNull
    private UUID identifier;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    public Person(@NotNull UUID identifier, @NotNull String firstName, @NotNull String lastName) {
        this.identifier = identifier;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person() {
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    @Override
    public Long getId() {
        return super.getId();
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return super.isNew();
    }
}
