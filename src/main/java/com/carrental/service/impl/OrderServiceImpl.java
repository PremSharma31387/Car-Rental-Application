package com.carrental.service.impl;

import com.carrental.entity.Coupon;
import com.carrental.entity.Customer;
import com.carrental.entity.Order;
import com.carrental.entity.Vehicle;
import com.carrental.exception.coupon.CouponUnavailableForCustomerException;
import com.carrental.exception.order.OrderNotFoundWithIdException;
import com.carrental.exception.order.RentalDaysIsInvalidException;
import com.carrental.exception.vehicle.VehicleIsNotPresentNowException;
import com.carrental.exception.vehicle.VehicleNotFoundWithIdException;
import com.carrental.repository.OrderRepository;
import com.carrental.repository.VehicleRepository;
import com.carrental.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CouponService couponService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;


    @Override
    public Order createOrder(Order order) throws VehicleNotFoundWithIdException {
        Customer customer = customerService.getCustomerById(order.getCustomer().getId());
        double discountByCoupon =0;
        Coupon coupon =null;
        if(order.getCouponCode()!=null) {
             coupon = couponService.getCouponByCouponCode(order.getCouponCode());

            boolean result = checkCouponForCustomer(customer, coupon.getCouponCode());


            if (!result) {
                throw new CouponUnavailableForCustomerException();
            }

            discountByCoupon = coupon.getDiscountPercentage();
        }
        Vehicle vehicle = vehicleService.getVehicleById(order.getVehicle().getId());
        if(!vehicle.isAvailable()){
            throw new VehicleIsNotPresentNowException();
        }

        int rentalDays=order.getRentalDays();
        double discountByCategory=categoryService.getCategoryById(vehicle.getCategoryId()).getDiscountPercent();


        double discountByRentalDays =0;
        if(rentalDays<0 || rentalDays>30){
            throw new RentalDaysIsInvalidException();
        }
        else if(rentalDays>=5 && rentalDays<10){
            discountByRentalDays=10;
        }
        else if(rentalDays>=10 && rentalDays<30){
            discountByRentalDays=20;
        }
        else{
            discountByRentalDays=30;
        }

        double totalDiscount=discountByCategory+discountByCoupon+discountByRentalDays;
        if(totalDiscount>100){
            totalDiscount=100;
        }

        double totalAmount=vehicle.getPricePerDay()*order.getRentalDays()*((totalDiscount)/100);

        Order newOrder = new Order();
        newOrder.setVehicle(vehicle);
        newOrder.setCustomer(customer);
        newOrder.setStatus("PLACED");
        if(coupon!=null){
            newOrder.setCouponCode(coupon.getCouponCode());
        }
        else{
            newOrder.setCouponCode(null);
        }
        newOrder.setRentalDays(order.getRentalDays());
        newOrder.setTotalAmount(totalAmount);
        vehicle.setAvailable(false);
        vehicleRepository.save(vehicle);
       return orderRepository.save(newOrder);
    }

    private boolean checkCouponForCustomer(Customer customer,String couponCode){
        List<Order> allCustomerOrder = getAllCustomerOrder(customer.getId());
        List<Coupon> couponForCustomer = couponService.getCouponForCustomer(allCustomerOrder.isEmpty());
        System.out.println(couponForCustomer);
        boolean result=false;
        for (Coupon c : couponForCustomer){
            if(c.getCouponCode().equals(couponCode)){
                result=true;
            }
        }
        return result;
    }

    @Override
    public List<Order> getAllCustomerOrder(Long customerId){
        Customer customer = customerService.getCustomerById(customerId);
        return orderRepository.findAllByCustomer(customer);
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()){
            throw new OrderNotFoundWithIdException();
        }
        return order.get();
    }

    @Override
    public Order completeOrder(Long id) {
        Order order = getOrderById(id);
        order.setStatus("COMPLETED");
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long id) {
        Order order = getOrderById(id);
        order.setStatus("CANCELLED");
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }


}

