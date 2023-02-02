package com.example.attornatus.controller;

import com.example.attornatus.model.Adress;
import com.example.attornatus.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/adress")
public class AdressController {

    @Autowired
    private AdressService adressService;

    @PostMapping("/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Adress saveAdress(@RequestBody Adress adress, @PathVariable("personId") Long personId) {
        return adressService.saveAdress(personId, adress);
    }

    @GetMapping("/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Set<Adress> getAdressesByPersonId(@PathVariable("personId") Long personId) {
        return adressService.getAdressesByPersonId(personId);
    }

    @GetMapping("mainAdress/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Adress getMainAdressByPersonId(@PathVariable("personId") Long personId) {
        return adressService.getMainAdressByPersonId(personId);
    }
}
