package com.example.attornatus.service;

import com.example.attornatus.model.Person;
import com.example.attornatus.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person getPersonById(Long idPerson) {
        return personRepository.findById(idPerson).get();
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }
}
