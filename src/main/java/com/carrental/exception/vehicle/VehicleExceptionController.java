package com.carrental.exception.vehicle;

import com.carrental.response.VehicleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VehicleExceptionController {

    @ExceptionHandler(value = VehicleNotFoundWithIdException.class)
    public ResponseEntity<VehicleResponse> exception(VehicleNotFoundWithIdException e){
        VehicleResponse vehicleResponse =new VehicleResponse();
        vehicleResponse.setStatusCode(404);
        vehicleResponse.setStatusDescription("Vehicle is not present with this id");
        return new ResponseEntity<>(vehicleResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = VehicleIsNotPresentNowException.class)
    public ResponseEntity<VehicleResponse> exception(VehicleIsNotPresentNowException e){
        VehicleResponse vehicleResponse =new VehicleResponse();
        vehicleResponse.setStatusCode(404);
        vehicleResponse.setStatusDescription("Vehicle is already booked ");
        return new ResponseEntity<>(vehicleResponse, HttpStatus.NOT_FOUND);
    }
}
