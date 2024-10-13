package com.carrental.response;

import com.carrental.entity.Coupon;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CouponResponse extends BaseResponse{
    private Coupon coupon;
    private Iterable<Coupon> couponsList;
}
