package com.carrental.service.impl;

import com.carrental.entity.Customer;
import com.carrental.exception.customer.CustomerNotFoundWithIdException;
import com.carrental.repository.CustomerRepository;
import com.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Iterable<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerById = customerRepository.findById(id);
        if(customerById.isEmpty()){
            throw new CustomerNotFoundWithIdException();
        }
        return customerById.get();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerById = getCustomerById(id);
        customerById.setName(customer.getName());
        customerById.setEmail(customer.getEmail());
        customerById.setPhone(customer.getPhone());
        customerById.setPassword(customer.getPassword());
        return customerRepository.save(customerById);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }
}
