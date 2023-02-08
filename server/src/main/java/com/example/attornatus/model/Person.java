package com.example.attornatus.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date birthDate;

    @OneToMany(cascade=CascadeType.MERGE, mappedBy="person", orphanRemoval=true)
    private Set<Address> addresses = new HashSet<>();

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Person() {
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

    public Set<Address> getAdresses() {
        return addresses;
    }

    public void setAdresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
