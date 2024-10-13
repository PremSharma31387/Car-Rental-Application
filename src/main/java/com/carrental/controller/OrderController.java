package com.carrental.controller;

import com.carrental.entity.Order;
import com.carrental.exception.vehicle.VehicleNotFoundWithIdException;
import com.carrental.repository.OrderRepository;
import com.carrental.response.OrderResponse;
import com.carrental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> get(@RequestBody Order order) {
        System.out.println(order);
        Order orderCreated = orderService.createOrder(order);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(201);
        orderResponse.setOrder(orderCreated);
        orderResponse.setStatusDescription("Order Created successfully");
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public  void delete(@PathVariable Long id){
        orderRepository.deleteById(id);
    }
}
