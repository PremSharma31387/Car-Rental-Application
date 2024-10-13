package com.carrental.exception.coupon;

import com.carrental.response.CouponResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CouponExceptionController {

    @ExceptionHandler(value = CouponNotFoundWithIdException.class)
    public ResponseEntity<CouponResponse> exception(CouponNotFoundWithIdException e){
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(404);
        couponResponse.setStatusDescription("Coupon is not present with this coupon code");
        return new ResponseEntity<>(couponResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CouponNameIsAlreadyExistException.class)
    public ResponseEntity<CouponResponse> exception(CouponNameIsAlreadyExistException e){
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(409);
        couponResponse.setStatusDescription("Coupon is already exist with this coupon code");
        return new ResponseEntity<>(couponResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = CouponUnavailableForCustomerException.class)
    public ResponseEntity<CouponResponse> exception(CouponUnavailableForCustomerException e){
        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setStatusCode(404);
        couponResponse.setStatusDescription("Coupon is not available for this customer");
        return new ResponseEntity<>(couponResponse, HttpStatus.CONFLICT);
    }
}

