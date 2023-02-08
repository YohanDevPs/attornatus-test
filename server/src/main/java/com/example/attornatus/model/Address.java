package com.example.attornatus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String street;

    @Column
    @NotNull
    private int cep;

    @Column
    @NotNull
    private int number;

    @Column
    @NotNull
    private String city;

    @Column(name = "isMainResidence")
    private boolean isMainResidence;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Address(String street, int cep, int number, String city, boolean isMainResidence) {
        this.street = street;
        this.cep = cep;
        this.number = number;
        this.city = city;
        this.isMainResidence = isMainResidence;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
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
