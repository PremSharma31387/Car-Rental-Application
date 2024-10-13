package com.carrental.exception.order;

import com.carrental.response.OrderResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionController {

    @ExceptionHandler(value = RentalDaysIsInvalidException.class)
    public ResponseEntity<OrderResponse> exception(RentalDaysIsInvalidException e){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(400);
        orderResponse.setStatusDescription("Rental days is invalid, Enter more than 0 and less than 30 days");
        return new ResponseEntity<>(orderResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrderNotFoundWithIdException.class)
    public ResponseEntity<OrderResponse> exception(OrderNotFoundWithIdException e){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(404);
        orderResponse.setStatusDescription("Order is not found with this id");
        return new ResponseEntity<>(orderResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OrderAlreadyCancelledException.class)
    public ResponseEntity<OrderResponse> exception(OrderAlreadyCancelledException e){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(400);
        orderResponse.setStatusDescription("Order already cancelled");
        return new ResponseEntity<>(orderResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrderAlreadyCompletedException.class)
    public ResponseEntity<OrderResponse> exception(OrderAlreadyCompletedException e){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setStatusCode(400);
        orderResponse.setStatusDescription("Order already completed");
        return new ResponseEntity<>(orderResponse, HttpStatus.BAD_REQUEST);
    }
}


