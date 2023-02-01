package com.example.attornatus.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String adress;

    @Column(nullable = false)
    private int CEP;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String city;

    public Adress() {
    }

    public Adress(Long id, String adress, int CEP, int number, String city) {
        this.id = id;
        this.adress = adress;
        this.CEP = CEP;
        this.number = number;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
