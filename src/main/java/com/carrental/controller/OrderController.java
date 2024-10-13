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

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Order order) {
        System.out.println(order);
        Order orderCreated = orderService.createOrder(order);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(201);
        orderResponse.setOrder(orderCreated);
        orderResponse.setStatusDescription("Order Created successfully");
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(200);
        orderResponse.setOrder(order);
        orderResponse.setStatusDescription("Order fetched successfully");
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<OrderResponse> getAllCustomerOrder(@PathVariable Long customerId){
        List<Order> allCustomerOrder = orderService.getAllCustomerOrder(customerId);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(200);
        orderResponse.setOrderList(allCustomerOrder);
        orderResponse.setStatusDescription("All Order fetched for customer successfully");
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping("complete/{id}")
    public ResponseEntity<OrderResponse> completeOrder(@PathVariable Long id){
        Order order = orderService.completeOrder(id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(201);
        orderResponse.setOrder(order);
        orderResponse.setStatusDescription("Order Complete Successfully");
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @PostMapping("cancel/{id}")
    public ResponseEntity<OrderResponse> cancelOrder(@PathVariable Long id){
        Order order = orderService.cancelOrder(id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(200);
        orderResponse.setOrder(order);
        orderResponse.setStatusDescription("Order Cancelled Successfully");
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @GetMapping("delete/{id}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(200);
        orderResponse.setStatusDescription("Order Deleted Successfully");
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }



}
