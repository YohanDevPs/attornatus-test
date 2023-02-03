package com.example.attornatus.service;

import com.example.attornatus.AttornatusApplicationTests;
import com.example.attornatus.repository.AddressRepository;
import com.example.attornatus.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressServiceTest extends AttornatusApplicationTests {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

}
