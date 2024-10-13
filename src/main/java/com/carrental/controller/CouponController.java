package com.carrental.controller;

import com.carrental.entity.Coupon;
import com.carrental.entity.Customer;
import com.carrental.entity.Order;
import com.carrental.response.CouponResponse;
import com.carrental.response.CustomerResponse;
import com.carrental.service.CouponService;
import com.carrental.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody Coupon coupon){
        Coupon couponCreated = couponService.createCoupon(coupon);
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(201);
        couponResponse.setStatusDescription("Coupon created successfully");
        couponResponse.setCoupon(couponCreated);
        return new ResponseEntity<>(couponResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CouponResponse> getAllCoupon(){
        Iterable<Coupon> allCoupon = couponService.getAllCoupon();
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(200);
        couponResponse.setCouponsList(allCoupon);
        couponResponse.setStatusDescription("Get All Coupon Fetched Successfully ");
        return new ResponseEntity<>(couponResponse, HttpStatus.CREATED);
    }

    @GetMapping("/code/{couponCode}")
    public ResponseEntity<CouponResponse> getCouponByCouponCode(@PathVariable String couponCode){
        Coupon couponByCouponCode = couponService.getCouponByCouponCode(couponCode);
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(200);
        couponResponse.setCoupon(couponByCouponCode);
        couponResponse.setStatusDescription("Get Coupon by coupon code Fetched Successfully ");
        return new ResponseEntity<>(couponResponse, HttpStatus.CREATED);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<CouponResponse> getAllCouponByCustomer(@PathVariable Long id){
        List<Order> allCustomerOrder = orderService.getAllCustomerOrder(id);
        List<Coupon> couponForCustomer = couponService.getCouponForCustomer(allCustomerOrder.isEmpty());
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(200);
        couponResponse.setStatusDescription("Get all coupon for customer fetched successfully");
        couponResponse.setCouponsList(couponForCustomer);
        return new ResponseEntity<>(couponResponse,HttpStatus.OK);
    }

    @PutMapping("/update/{couponCode}")
    public ResponseEntity<CouponResponse> updateCoupon(@PathVariable String couponCode,@RequestBody Coupon coupon){
        Coupon couponUpdated = couponService.updateCoupon(couponCode,coupon);
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(201);
        couponResponse.setCoupon(couponUpdated);
        couponResponse.setStatusDescription("Coupon updated successfully");
        return new ResponseEntity<>(couponResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{couponCode}")
    public ResponseEntity<CouponResponse> deleteCoupon(@PathVariable String couponCode){
        couponService.deleteCoupon(couponCode);
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(200);
        couponResponse.setStatusDescription("Coupon deleted successfully");
        return new ResponseEntity<>(couponResponse, HttpStatus.CREATED);
    }
}
