package com.gklyphon.Customer.repository;

import com.gklyphon.Customer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
