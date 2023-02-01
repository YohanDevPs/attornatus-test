package com.example.attornatus.service;

import com.example.attornatus.model.Adress;

public interface AdressService {

    Adress saveAdress(Long personId, Adress adress);
}
