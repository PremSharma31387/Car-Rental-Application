package com.carrental.response;

import com.carrental.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderResponse extends BaseResponse{
    private Order order;
    private List<Order> orderList;
}
