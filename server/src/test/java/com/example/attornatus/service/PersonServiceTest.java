package com.example.attornatus.service;

import com.example.attornatus.exception.NotFoundElementException;
import com.example.attornatus.model.Person;
import com.example.attornatus.repository.AddressRepository;
import com.example.attornatus.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {

    @MockBean
    private AddressRepository addressRepository;
    @MockBean
    private PersonRepository personRepository;
    @Autowired
    private PersonService personService;

    @Test
    public void mustTestGetPersonById() {
        Long personId = 1L;

        Person person = createPerson();

        Mockito.when(personRepository.findById(ArgumentMatchers.eq(personId)))
                .thenReturn(Optional.ofNullable(person));

        assertThrows(NotFoundElementException.class, () -> personService.getPersonById(null));

        personService.getPersonById(personId);
        Mockito.verify(personRepository, Mockito.times(1))
                .findById(ArgumentMatchers.eq(personId));
    }

    @Test
    public void mustTestGetAllPersons() {
        List<Person> personCollection = createListOfPersons();

        Mockito.when(personRepository.findAll()).thenReturn(personCollection);

        personService.getAllPersons();
        Mockito.verify(personRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    public void mustTestSavePerson() {
        Person person = createPerson();
        personService.savePerson(person);
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
    }

    @Test
    public void mustTestDeletePersonByIdMethod() {
        Long personId = 1L;

        Person person = createPerson();

        Mockito.when(personRepository.findById(ArgumentMatchers.eq(personId)))
                .thenReturn(Optional.ofNullable(person));

        assertThrows(NotFoundElementException.class, () -> personService.deletePersonById(null));

        personService.deletePersonById(personId);
        Mockito.verify(personRepository, Mockito.times(1)).delete(person);
    }


    public Person createPerson() {
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1L);
        Mockito.when(person.getName()).thenReturn("Yohan");
        Mockito.when(person.getAdresses()).thenReturn(Set.copyOf(Collections.emptyList()));
        Mockito.when(person.getBirthDate()).thenReturn(new Date());
        return person;
    }

    public List<Person> createListOfPersons() {

        List<Person> persons = Mockito.mock(List.class);

        var person1 = new Person("Yohan", new Date());
        var person2 = new Person("Natalia", new Date());

        Mockito.when(persons.get(0)).thenReturn(person1);
        Mockito.when(persons.get(1)).thenReturn(person2);

        return persons;
    }
}
