package com.carrental.repository;

import com.carrental.entity.Customer;
import com.carrental.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findAllByCustomer(Customer customer);
}
