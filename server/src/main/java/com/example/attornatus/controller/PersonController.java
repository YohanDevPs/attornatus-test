package com.example.attornatus.controller;

import com.example.attornatus.model.Person;
import com.example.attornatus.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person getPersonById(@PathVariable("id") Long id) {
        return personService.getPersonById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> personList() {
        return personService.personList();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFarmById(@PathVariable("id") Long id) {
        personService.deletePersonById(id);
    };

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@PathVariable("id") Long id, @RequestBody Person person) {
        personService.updatePerson(id, person);
    }
}


