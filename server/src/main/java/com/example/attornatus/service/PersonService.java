package com.example.attornatus.service;

import com.example.attornatus.model.Person;

import java.util.List;

public interface PersonService {

    Person getPersonById(Long idPerson);

    List<Person> personList();

    Person savePerson(Person person);

    void deletePersonById(Long id);
}
