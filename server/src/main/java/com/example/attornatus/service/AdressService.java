package com.example.attornatus.service;

import com.example.attornatus.model.Adress;

import java.util.List;
import java.util.Set;

public interface AdressService {

    Adress saveAdress(Long personId, Adress adress);

    Set<Adress> getAdressesByPersonId(Long personId);

    Adress getMainAdressByPersonId(Long personId);
}
