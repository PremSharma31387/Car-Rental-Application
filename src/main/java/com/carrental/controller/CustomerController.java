package com.carrental.controller;

import com.carrental.entity.Customer;
import com.carrental.response.CustomerResponse;
import com.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer){
        Customer customerCreated = customerService.createCustomer(customer);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomer(customerCreated);
        customerResponse.setStatusCode(201);
        customerResponse.setStatusDescription("Customer created successfully");
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CustomerResponse> getAllCustomer(){
        Iterable<Customer> allCustomer = customerService.getAllCustomer();
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomerList(allCustomer);
        customerResponse.setStatusCode(200);
        customerResponse.setStatusDescription("Get All Customer Fetched Successfully ");
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id){
        Customer customer = customerService.getCustomerById(id);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomer(customer);
        customerResponse.setStatusCode(200);
        customerResponse.setStatusDescription("Get Customer by id Fetched Successfully ");
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody Customer customer,@PathVariable Long id){
        Customer customerUpdated = customerService.updateCustomer(id,customer);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setCustomer(customerUpdated);
        customerResponse.setStatusCode(201);
        customerResponse.setStatusDescription("Customer updated successfully");
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setStatusCode(200);
        customerResponse.setStatusDescription("Customer deleted successfully");
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }







}
