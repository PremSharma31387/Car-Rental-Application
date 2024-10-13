package com.carrental.service;

import com.carrental.entity.Coupon;
import com.carrental.entity.Customer;
import com.carrental.entity.Order;
import com.carrental.exception.vehicle.VehicleNotFoundWithIdException;

import java.util.List;

public interface OrderService {

    public Order createOrder(Order order);
    public List<Order> getAllCustomerOrder(Long customerId);
    public Order getOrderById(Long id);
    public Order completeOrder(Long id);
    public Order cancelOrder(Long id);
    public void deleteOrder(Long id);
}
