package com.carrental.exception.customer;

import com.carrental.response.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionController {

    @ExceptionHandler(value = CustomerNotFoundWithIdException.class)
    public ResponseEntity<CustomerResponse> exception(CustomerNotFoundWithIdException e){
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setStatusCode(404);
        customerResponse.setStatusDescription("Customer is not present with this id");
        return new ResponseEntity<>(customerResponse, HttpStatus.NOT_FOUND);
    }
}
