package com.carrental.response;

import com.carrental.entity.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleResponse extends BaseResponse{
    private Iterable<Vehicle> vehicleList;
    private Vehicle vehicle;
}
