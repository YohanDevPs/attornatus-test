package com.example.attornatus.service;

import com.example.attornatus.model.Adress;
import com.example.attornatus.model.Person;
import com.example.attornatus.repository.AdressRepository;
import com.example.attornatus.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdressSeviceImpl implements AdressService{

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Adress saveAdress(Long personId, Adress adress) {
        Person person = personRepository.getById(personId);

        if(person.getAdresses().isEmpty()) {
            adress.setMainResidence(true);
        }

        adress.setPerson(person);
        person.getAdresses().add(adress);
        adressRepository.save(adress);

        return adress;
    }

    @Override
    public Set<Adress> getAdressesByPersonId(Long personId) {
         return personRepository.findById(personId).get().getAdresses();
    }

    @Override
    public Adress getMainAdressByPersonId(Long personId) {
        Adress mainAdress = personRepository
                .getReferenceById(personId)
                .getAdresses()
                .stream()
                .filter(c -> c.isMainResidence() == true)
                .collect(Collectors.toList())
                .get(0);


        return mainAdress;
    }

}
