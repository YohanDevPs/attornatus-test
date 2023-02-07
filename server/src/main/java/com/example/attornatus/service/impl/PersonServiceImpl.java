package com.example.attornatus.service.impl;

import com.example.attornatus.exception.NotFoundElementException;
import com.example.attornatus.model.Person;
import com.example.attornatus.repository.PersonRepository;
import com.example.attornatus.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person getPersonById(Long idPerson) {
        return personRepository.findById(idPerson)
                .orElseThrow(() -> new NotFoundElementException("Pessoa não encontrada: " + idPerson));
    }

    @Override
    public List<Person> getAllPersons() {
         return personRepository.findAll();
    }

    @Override
    public Person savePerson(Person person) {
         return personRepository.save(person);
    }

    @Override
    public void deletePersonById(Long idPerson) {
        Person person = personRepository.findById(idPerson)
                .orElseThrow(() -> new NotFoundElementException("Pessoa não encontrada " + idPerson));

        personRepository.delete(person);
    }

    @Override
    public Person updatePerson(Long idPerson,Person person) {
        person.setId(idPerson);
        return personRepository.save(person);
    }
}
