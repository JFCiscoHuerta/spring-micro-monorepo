package com.gklyphon.Customer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gklyphon.Customer.Data;
import com.gklyphon.Customer.model.entity.Customer;
import com.gklyphon.Customer.repository.ICustomerRepository;
import com.gklyphon.Customer.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerRestControllerTest {

    @MockBean
    ICustomerRepository customerRepository;

    @MockBean
    CustomerServiceImpl customerService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;
    String API_URL = "/v1/customers";

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getCustomer_shouldReturnCustomer_whenGetCustomerByIdCalled() throws Exception {
        when(customerService.findCustomerById(1L)).thenReturn(Data.CUSTOMER);
        mockMvc.perform(
                MockMvcRequestBuilders.get(API_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.id").value(1L));
        verify(customerService, times(1)).findCustomerById(1L);
    }

    @Test
    void saveCustomer_shouldSaveCustomer_whenSaveCustomerCalled() throws Exception {
        when(customerService.saveCustomer(Data.CUSTOMER)).thenReturn(Data.CUSTOMER);
        mockMvc.perform(
                MockMvcRequestBuilders.post(API_URL + "/save-customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Data.CUSTOMER))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Alberto"));
        verify(customerService).saveCustomer(Data.CUSTOMER);
    }

    @Test
    void updateCustomer_shouldUpdateCustomer_whenUpdateCustomerCalled() throws Exception {
        when(customerService.updateCustomer(1L, Data.CUSTOMER)).thenReturn(Data.CUSTOMER);
        mockMvc.perform(
                MockMvcRequestBuilders.put(API_URL + "/update-customer/1")
                        .content(objectMapper.writeValueAsString(Data.CUSTOMER))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Alberto"));
        verify(customerService).updateCustomer(1L, Data.CUSTOMER);
    }

    @Test
    void deleteCustomer_shouldDeleteCustomer_whenDeleteCustomerCalled() throws Exception {
        //when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerService).deleteCustomerById(1L);
        mockMvc.perform(
                MockMvcRequestBuilders.delete(API_URL + "/delete-customer/1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }
}