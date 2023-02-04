package com.example.attornatus.service;

import com.example.attornatus.model.Address;
import com.example.attornatus.model.Person;
import com.example.attornatus.repository.AddressRepository;
import com.example.attornatus.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressServiceTest {

    @MockBean
    private AddressRepository addressRepository;
    @MockBean
    private PersonService personService;
    @MockBean
    private PersonRepository personRepository;
    @Autowired
    private AddressService addressService;

    @Test
    public void mustSaveAddressesInPersonId() {
        Person person = createPerson();

        Long personId = 1L;

        Mockito.when(personService.getPersonById(personId)).thenReturn(person);
    }

    @Test
    public void mustGetAddressesByPersonId() {
        Person person = createPerson();

        Set<Address> addresses = Mockito.mock(Set.class);

        Mockito.when(person.getAdresses())
                        .thenReturn(addresses);
    }

    @Test
    public void mustGetMainAddressesByPersonId() {
        Long personId = 1L;
        Person person = createPerson();

        Mockito.when(personService.getPersonById(personId))
                .thenReturn(person);

        addressService.getMainAddressByPersonId(personId);
    }

    public Address createAddress() {
        Address address = Mockito.mock(Address.class);
        Mockito.when(address.getId()).thenReturn(1L);
        Mockito.when(address.getAddress()).thenReturn("Rua Brasil");
        Mockito.when(address.getCEP()).thenReturn(71320400);
        Mockito.when(address.isMainResidence()).thenReturn(true);
        return address;
    }

    public Person createPerson() {
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getId()).thenReturn(1L);
        Mockito.when(person.getName()).thenReturn("Yohan");
        Mockito.when(person.getAdresses()).thenReturn(Set.copyOf(Collections.emptyList()));
        Mockito.when(person.getBirthDate()).thenReturn(new Date());

        Mockito.when(person.getAdresses())
                .thenReturn(Set.of(new Address("Rua Brasil", 76888999, 123, "Salvador", true)));

        return person;
    }
}
