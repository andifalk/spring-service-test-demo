# Spring Service Layer Testing Demo

This repository contains code for demonstrating integration testing a spring web application
on the service layer with real jpa repository (i.e. no mocking).

The application just consists of 

The *PersonServiceIntegrationTest* tests the *PersonService* together with the *PersonRepository*
on a H2 in-memory database. The integration test runs pretty fast as it only starts a minimum spring context
without the web part (which is not required on service layer).