package com.example.attornatus.controller;

import com.example.attornatus.model.Person;
import com.example.attornatus.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    MockMvc mockMvc;
    @Autowired
    private PersonController personController;
    @MockBean
    private PersonService personService;
    private List<Person> persons;

    private String url = "/v1/person";

    @BeforeEach
    public void initPersonList() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).setControllerAdvice().build();
        persons = new ArrayList<Person>();

        persons.add(new Person("Yohan", new Date()));
        persons.add(new Person("Pedro", new Date()));
    }

    @Test
    public void mustReturnIsOk_WhenPersonById() throws Exception {
        BDDMockito.given(personService.getPersonById(1L)).willReturn(persons.get(0));

        this.mockMvc.perform(get(url + "/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value(persons.get(0).getName()));
    }

    @Test
    public void mustCreatePerson_andReturnIsCreated() throws Exception {
        Person person = new Person("Jose", new Date());

        ObjectMapper mapper = new ObjectMapper();


        String personAsJson = mapper.writeValueAsString(person);

        this.mockMvc.perform(post(url).content(personAsJson).accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
    }


}