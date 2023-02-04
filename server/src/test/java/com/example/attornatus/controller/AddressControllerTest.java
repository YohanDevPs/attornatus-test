package com.example.attornatus.controller;

import com.example.attornatus.exception.NotFoundElementException;
import com.example.attornatus.model.Address;
import com.example.attornatus.service.AddressService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;;
    @Autowired
    private AddressController addressController;
    @MockBean
    private AddressService addressService;
    private String url = "/v1/address";

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(addressController)
                .setControllerAdvice()
                .build();
    }

    @Test
    public void testEndpointCreateAddressInPersonId() throws Exception {
        var address = new Address();
        address.setAddress("Rua Brasil");
        address.setCEP(12344);
        address.setNumber(1244);
        address.setCity("Sao Paulo");

        Long personId = 1L;

        Mockito.when(addressService.saveAddressInPersonById(address, personId)).thenReturn(address);

        ObjectMapper mapper = new ObjectMapper();

        var jsonAddress = mapper.writeValueAsString(address);

        this.mockMvc.perform(post(url+"/{personId}", personId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAddress))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAdressesByPersonId() throws Exception {
        var address = new Address("Rua Brasil", 76888999, 123, "Salvador", true);
        Long personId = 1L;

        Mockito.when(addressService.getAddressesByPersonId(personId)).thenReturn(Set.of(address));

        ObjectMapper mapper = new ObjectMapper();

        var jsonAddress = mapper.writeValueAsString(Set.of(address));

        this.mockMvc.perform(get(url+"/{personId}", personId))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAddress));
    }

    @Test
    public void testGetAMainAdressByPersonId() throws Exception {
        var address = new Address("Rua Brasil", 76888999, 123, "Salvador", true);
        Long personId = 1L;

        Mockito.when(addressService.getMainAddressByPersonId(personId)).thenReturn(address);

        ObjectMapper mapper = new ObjectMapper();

        var jsonAddress = mapper.writeValueAsString(address);

        this.mockMvc.perform(get(url+"/mainAddress/personId/{personId}", personId))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonAddress));
    }
}
