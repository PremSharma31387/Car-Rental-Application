package com.carrental.service.impl;

import com.carrental.entity.Coupon;
import com.carrental.exception.coupon.CouponNameIsAlreadyExistException;
import com.carrental.exception.coupon.CouponNotFoundWithIdException;
import com.carrental.repository.CouponRepository;
import com.carrental.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        coupon.setCouponCode(coupon.getCouponCode().toUpperCase());
        Optional<Coupon> couponById = couponRepository.findById(coupon.getCouponCode());
        if(couponById.isPresent()){
            throw new CouponNameIsAlreadyExistException();
        }
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon getCouponByCouponCode(String couponCode) {
        Optional<Coupon> coupon = couponRepository.findById(couponCode.toUpperCase());
        if(coupon.isEmpty()){
            throw new CouponNotFoundWithIdException();
        }
        return coupon.get();
    }

    @Override
    public List<Coupon> getAllCoupon() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon updateCoupon(String couponCode, Coupon coupon) {
        Coupon couponByCouponCode = getCouponByCouponCode(couponCode);
        couponByCouponCode.setCouponName(coupon.getCouponName());
        couponByCouponCode.setDiscountPercentage(coupon.getDiscountPercentage());
        return couponRepository.save(couponByCouponCode);
    }

    @Override
    public void deleteCoupon(String couponCode) {
        Coupon coupon = getCouponByCouponCode(couponCode);
        couponRepository.delete(coupon);
    }

    @Override
    public List<Coupon> getCouponForCustomer(boolean available) {
        List<Coupon> allCoupon = getAllCoupon();
        if(!available){
            for(Coupon c : allCoupon){
                if(c.getCouponCode().equals("FIRST50")){
                    allCoupon.remove(c);
                   break;
                }
            }
        }
        return allCoupon;
    }


}
