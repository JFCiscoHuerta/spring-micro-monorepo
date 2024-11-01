package com.gklyphon.Customer.controller;

import com.gklyphon.Customer.model.entity.Customer;
import com.gklyphon.Customer.service.ICustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final ICustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(
                @PathVariable(name = "id") Long id
    ) {
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/save-customer")
    public ResponseEntity<?> saveCustomer(
            @Valid @RequestBody Customer customer,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
        Customer customerSaved = customerService.saveCustomer(customer);
        return new ResponseEntity<>(customerSaved, HttpStatus.CREATED);
    }

    @PutMapping("/update-customer/{id}")
    public ResponseEntity<?> saveCustomer(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody Customer customer,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
        Customer customerUpdated = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<?> deleteCustomer(
            @PathVariable(name = "id") Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
