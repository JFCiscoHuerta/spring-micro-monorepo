package com.gklyphon.Customer;

import com.gklyphon.Customer.model.entity.Customer;

import java.time.LocalDateTime;

public class Data {

    public static final Customer CUSTOMER = new Customer(1L, "Alberto", "alberto@gmail.com",
            "1234567890", LocalDateTime.now(), LocalDateTime.now());

}
