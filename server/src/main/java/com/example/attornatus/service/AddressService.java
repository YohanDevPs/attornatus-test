package com.example.attornatus.service;

import com.example.attornatus.model.Address;

import java.util.Set;

public interface AddressService {

    Address saveAddressInPersonById(Address address, Long personId);

    Set<Address> getAddressesByPersonId(Long personId);

    Address getMainAddressByPersonId(Long personId);
}
