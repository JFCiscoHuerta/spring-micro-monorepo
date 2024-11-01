package com.gklyphon.Customer.service;

import com.gklyphon.Customer.Data;
import com.gklyphon.Customer.exception.custom.ElementNotFoundException;
import com.gklyphon.Customer.model.entity.Customer;
import com.gklyphon.Customer.repository.ICustomerRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

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
    void findCustomerById_shouldThrowElementNotFoundException_whenIdDoNotExists() {
        doThrow(ElementNotFoundException.class).when(customerRepository).findById(100L);
        assertThrows(ElementNotFoundException.class, ()-> customerService.findCustomerById(100L));
        verify(customerRepository, times(1)).findById(100L);
    }

    @Test
    void saveCustomer_shouldReturnCustomer_whenSaveCustomerSuccessfully() {
        when(customerRepository.save(Data.CUSTOMER)).thenReturn(Data.CUSTOMER);
        Customer customer = customerService.saveCustomer(Data.CUSTOMER);
        assertAll(()->{
            assertNotNull(customer);
            assertEquals(Data.CUSTOMER.getId(), customer.getId());
        });
        verify(customerRepository, times(1)).save(Data.CUSTOMER);
    }

    @Test
    void updateCustomer_shouldReturnCustomer_whenUpdateCustomerSuccessfully() {
        when(customerRepository.save(Data.CUSTOMER)).thenReturn(Data.CUSTOMER);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(Data.CUSTOMER));
        Customer customer = customerService.updateCustomer(1L, Data.CUSTOMER);
        assertAll(()->{
            assertNotNull(customer);
            assertEquals(Data.CUSTOMER.getId(), customer.getId());
        });
        verify(customerRepository, times(1)).save(Data.CUSTOMER);
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    @Disabled
    void updatePassword_whenUpdatePasswordSuccessfully() {

    }

    @Test
    @Disabled
    void updateEmail_whenUpdateEmailSuccessfully() {

    }

    @Test
    void deleteCustomer_shouldReturnTrue_whenDeleteCustomerByIdSuccessfully() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        assertDoesNotThrow(()-> {customerService.deleteCustomerById(1L);});
        verify(customerRepository, times(1)).deleteById(1L);
        verify(customerRepository, times(1)).existsById(1L);
    }
}
