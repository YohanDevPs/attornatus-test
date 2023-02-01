package com.example.attornatus.service;

import com.example.attornatus.model.Adress;
import com.example.attornatus.model.Person;
import com.example.attornatus.repository.AdressRepository;
import com.example.attornatus.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdressSeviceImpl implements AdressService{

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Adress saveAdress(Long personId, Adress adress) {
        Person person = personRepository.getById(personId);
        adress.setPerson(person);
        person.getAdressSet().add(adress);
        adressRepository.save(adress);

        return adress;
    }

}
