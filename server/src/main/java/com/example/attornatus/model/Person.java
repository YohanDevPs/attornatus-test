package com.example.attornatus.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date birthDate;

    @OneToMany(cascade=CascadeType.MERGE, mappedBy="person", orphanRemoval=true)
    private Set<Adress> adresses = new HashSet<>();

    public Person() {
    }

    public Person(Long id, String name, Date birthDate, Set<Adress> adresses) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.adresses = adresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Adress> getAdressSet() {
        return adresses;
    }

    public void setAdressSet(Set<Adress> adresses) {
        this.adresses = adresses;
    }
}
