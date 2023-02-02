package com.example.attornatus.service;

import com.example.attornatus.exception.NotFoundElementException;
import com.example.attornatus.model.Adress;
import com.example.attornatus.model.Person;
import com.example.attornatus.repository.AdressRepository;
import com.example.attornatus.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdressSeviceImpl implements AdressService{

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Adress saveAdressInPersonById(Long personId, Adress adress) {
        Person person = personRepository
                .findById(personId)
                .orElseThrow(() -> new NotFoundElementException("Pessoa não encontrada: " + personId));

        if(person.getAdresses().isEmpty()) {
            adress.setMainResidence(true);
        }

        adress.setPerson(person);
        person.getAdresses().add(adress);

        return adressRepository.save(adress);
    }

    @Override
    public Set<Adress> getAdressesByPersonId(Long personId) {
         return personRepository
                 .findById(personId)
                 .orElseThrow(() -> new NotFoundElementException("Pessoa não encontrada: " + personId))
                 .getAdresses();
    }

    @Override
    public Adress getMainAdressByPersonId(Long personId) {

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NotFoundElementException("Pessoa não encontrada: " + personId));

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
