package com.gklyphon.Customer.service;

import com.gklyphon.Customer.Data;
import com.gklyphon.Customer.model.entity.Customer;
import com.gklyphon.Customer.repository.ICustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    ICustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    @DisplayName("Should return customer when ID exists")
    void findCustomerById_ShouldReturnCustomer_WhenIdExists() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(Data.CUSTOMER));
        Customer customer = customerService.findCustomerById(1L);
        assertAll(()->{
            assertNotNull(customer, "Customer should not be null");
            assertEquals(Data.CUSTOMER.getId(), customer.getId(), "IDs should match");
            assertEquals(Data.CUSTOMER.getName(), customer.getName(), "Names should match");
        });
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void name() {
    }
}
