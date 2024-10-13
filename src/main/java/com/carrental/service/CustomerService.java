package com.carrental.service;

import com.carrental.entity.Customer;

public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public Iterable<Customer> getAllCustomer();
    public Customer getCustomerById(Long id);
    public Customer updateCustomer(Long id,Customer customer);
    public void deleteCustomer(Long id);
}
