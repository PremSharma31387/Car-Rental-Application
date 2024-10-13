package com.carrental.response;

import com.carrental.entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerResponse extends BaseResponse{
    private Customer customer;
    private Iterable<Customer> customerList;
}
