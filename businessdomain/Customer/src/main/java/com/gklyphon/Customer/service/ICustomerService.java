package com.gklyphon.Customer.service;

import com.gklyphon.Customer.model.entity.Customer;

public interface ICustomerService {

    Customer findCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    boolean deleteCustomerById(Long id);
}
