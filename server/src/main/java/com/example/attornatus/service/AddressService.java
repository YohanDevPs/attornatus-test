package com.example.attornatus.service;

import com.example.attornatus.model.Address;

import java.util.Set;

public interface AddressService {

    Address saveAddressInPersonById(Long personId, Address address);

    Set<Address> getAddressesByPersonId(Long personId);

    Address getMainAddressByPersonId(Long personId);
}
