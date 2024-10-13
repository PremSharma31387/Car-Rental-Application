package com.carrental.service;

import com.carrental.entity.Coupon;

import java.util.List;

public interface CouponService {
    public Coupon createCoupon(Coupon coupon);
    public Coupon getCouponByCouponCode(String couponCode);
    public List<Coupon> getAllCoupon();
    public Coupon updateCoupon(String couponCode,Coupon coupon);
    public void deleteCoupon(String couponCode);
    public List<Coupon> getCouponForCustomer(boolean available);
}
