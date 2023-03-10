package com.example.attornatus.controller;

import com.example.attornatus.model.Person;
import com.example.attornatus.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private PersonController personController;
    @MockBean
    private PersonService personService;

    private String url = "/v1/person";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setControllerAdvice()
                .build();
    }

    @Test
    public void testEndpointDeletionByIdPerson() throws Exception {
        Long idPerson = 1L;
        Mockito.doNothing().when(personService).deletePersonById(idPerson);

        this.mockMvc.perform(MockMvcRequestBuilders.delete(url+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    public void testEndpointCreatePerson() throws Exception {
        var person = new Person();
        person.setName("Yohan");
        person.setBirthDate(new Date(2000, 2, 1));
        Mockito.when(personService.savePerson(Mockito.any())).thenReturn(person);

        ObjectMapper mapper = new ObjectMapper();

        var jsonPerson = mapper.writeValueAsString(person);

        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPerson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(jsonPerson));
    }

    @Test
    public void testEndpointGetListPersons() throws Exception {
        var persons = new Person("Yohan", new Date(2000, 2, 1));
        Mockito.when(personService.getAllPersons()).thenReturn(List.of(persons));

        ObjectMapper mapper = new ObjectMapper();

        var json = mapper.writeValueAsString(List.of(persons));

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void testEndpointGetPersonById() throws Exception {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Yohan", new Date()));
        persons.add(new Person("Pedro", new Date()));

        BDDMockito.given(personService.getPersonById(1L)).willReturn(persons.get(0));

        this.mockMvc.perform(get(url + "/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value(persons.get(0).getName()));
    }
}