package com.example.attornatus.service;

import com.example.attornatus.exception.NotFoundElementException;
import com.example.attornatus.model.Address;
import com.example.attornatus.model.Person;
import com.example.attornatus.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressSeviceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonService personService;

    @Override
    public Address saveAddressInPersonById(Long personId, Address address) {
        Person person = personService.getPersonById(personId);

        if(person.getAdresses().isEmpty()) {
            address.setMainResidence(true);
        }

        address.setPerson(person);
        person.getAdresses().add(address);

        return addressRepository.save(address);
    }

    @Override
    public Set<Address> getAddressesByPersonId(Long personId) {
         return personService.getPersonById(personId).getAdresses();
    }

    @Override
    public Address getMainAddressByPersonId(Long personId) {
        Person person = personService.getPersonById(personId);

        if(person.getAdresses().isEmpty()) {
            throw new NotFoundElementException("Endereço não encontrado: " + personId);
        }

        return person.getAdresses()
                .stream()
                .filter(c -> c.isMainResidence() == true)
                .collect(Collectors.toList())
                .get(0);
    }
}
