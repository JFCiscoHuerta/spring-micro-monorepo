package com.gklyphon.Customer.service;

import com.gklyphon.Customer.exception.custom.ElementNotFoundException;
import com.gklyphon.Customer.model.entity.Customer;
import com.gklyphon.Customer.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                ()-> new ElementNotFoundException("Customer with id: " + id +" not found"));
    }

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        customer.setCreateAt(LocalDateTime.now());
        customer.setUpdateAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public Customer updateCustomer(Long id, Customer customer) {
        Customer originalCustomer = findCustomerById(id);
        originalCustomer.setName(customer.getName());
        originalCustomer.setPhone(customer.getPhone());
        originalCustomer.setCreateAt(LocalDateTime.now());
        originalCustomer.setUpdateAt(LocalDateTime.now());
        return customerRepository.save(originalCustomer);
    }

    @Override
    @Transactional
    public boolean deleteCustomerById(Long id) {
        if (!customerRepository.existsById(id)) {
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }
}
