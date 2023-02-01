package com.example.attornatus.controller;

import com.example.attornatus.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/adress")
public class AdressController {

    @Autowired
    private AdressService adressService;

}
