package com.example.attornatus.controller;

import com.example.attornatus.model.Address;
import com.example.attornatus.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Address saveAddressInPersonById(@RequestBody Address Address, @PathVariable("personId") Long personId) {
        return addressService.saveAddressInPersonById(personId, Address);
    }

    @GetMapping("/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Set<Address> getAddressesByPersonId(@PathVariable("personId") Long personId) {
        return addressService.getAddressesByPersonId(personId);
    }

    @GetMapping("mainAddress/personId/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Address getMainAddressByPersonId(@PathVariable("personId") Long personId) {
        return addressService.getMainAddressByPersonId(personId);
    }
}
