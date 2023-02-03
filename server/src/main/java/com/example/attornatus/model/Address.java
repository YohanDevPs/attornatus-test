package com.example.attornatus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int CEP;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private String city;

    @Column(name = "isMainResidence", nullable = false)
    private boolean isMainResidence;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Address(String address, int CEP, int number, String city, boolean isMainResidence) {
        this.address = address;
        this.CEP = CEP;
        this.number = number;
        this.city = city;
        this.isMainResidence = isMainResidence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean isMainResidence() {
        return isMainResidence;
    }

    public void setMainResidence(boolean mainResidence) {
        isMainResidence = mainResidence;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
