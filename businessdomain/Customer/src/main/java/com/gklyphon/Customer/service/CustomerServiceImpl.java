package com.gklyphon.Customer.service;

import com.gklyphon.Customer.model.entity.Customer;
import com.gklyphon.Customer.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }
}
