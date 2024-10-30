package com.gklyphon.Customer.service;

import com.gklyphon.Customer.model.entity.Customer;

public interface ICustomerService {

    Customer findCustomerById(Long id);

}
