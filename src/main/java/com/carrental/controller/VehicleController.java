package com.carrental.controller;

import com.carrental.entity.Vehicle;
import com.carrental.exception.vehicle.VehicleNotFoundWithIdException;
import com.carrental.response.CategoryResponse;
import com.carrental.response.VehicleResponse;
import com.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

//    Get All Vehicle Mapping
    @GetMapping
    public ResponseEntity<VehicleResponse> getAllVehicle(){
        Iterable<Vehicle> allVehicle = vehicleService.getAllVehicle();
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setStatusCode(201);
        vehicleResponse.setStatusDescription("Vehicle created successfully");
        vehicleResponse.setVehicleList(allVehicle);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.OK);
    }

    //    Create Vehicle Mapping
    @PostMapping("/create")
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody Vehicle vehicle) throws VehicleNotFoundWithIdException {
        Vehicle vehicleCreated = vehicleService.createVehicle(vehicle);
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setStatusCode(201);
        vehicleResponse.setStatusDescription("Vehicle created successfully");
        vehicleResponse.setVehicle(vehicleCreated);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.CREATED);
    }

    //    Get Vehicle By Id Mapping
    @GetMapping("id/{id}")
    public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Long id) throws VehicleNotFoundWithIdException {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setStatusCode(200);
        vehicleResponse.setStatusDescription("Vehicle is present with this id");
        vehicleResponse.setVehicle(vehicle);
        return new ResponseEntity<>(vehicleResponse,HttpStatus.OK);
    }

    //    Get Vehicle by Category Id Mapping
    @GetMapping("categoryId/{id}")
    public ResponseEntity<VehicleResponse> getVehicleListByCategoryId(@PathVariable("id") Long categoryId) throws VehicleNotFoundWithIdException {
        Iterable<Vehicle> vehicleList = vehicleService.getVehicleListByCategoryId(categoryId);
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setStatusCode(200);
        vehicleResponse.setStatusDescription("Vehicle is present with this id");
        vehicleResponse.setVehicleList(vehicleList);
        return new ResponseEntity<>(vehicleResponse,HttpStatus.OK);
    }

    @GetMapping("categoryName/{name}")
    public ResponseEntity<VehicleResponse> getVehicleListByCategoryName(@PathVariable("name") String categoryName) throws VehicleNotFoundWithIdException {
        Iterable<Vehicle> vehicleList = vehicleService.getVehicleListByCategoryName(categoryName.toUpperCase());
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setStatusCode(200);
        vehicleResponse.setStatusDescription("Vehicle is present with this Name");
        vehicleResponse.setVehicleList(vehicleList);
        return new ResponseEntity<>(vehicleResponse,HttpStatus.OK);
    }

    //    Update Vehicle Mapping
    @PutMapping("/update/{id}")
    public ResponseEntity<VehicleResponse> updateVehicle(@RequestBody Vehicle vehicle,@PathVariable Long id) throws VehicleNotFoundWithIdException {
        Vehicle vehicleUpdated = vehicleService.updateVehicle(id,vehicle);
        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setStatusCode(201);
        vehicleResponse.setStatusDescription("Vehicle updated successfully");
        vehicleResponse.setVehicle(vehicleUpdated);
        return new ResponseEntity<>(vehicleResponse, HttpStatus.CREATED);
    }

//    Delete Vehicle Mapping
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<VehicleResponse> deleteVehicle(@PathVariable Long id) throws VehicleNotFoundWithIdException {
        vehicleService.deleteVehicle(id);
        VehicleResponse vehicleResponse =new VehicleResponse();
        vehicleResponse.setStatusCode(201);
        vehicleResponse.setStatusDescription("Vehicle deleted successfully");
        return new ResponseEntity<>(vehicleResponse, HttpStatus.OK);
    }


    @PutMapping("/return/{id}")
    public ResponseEntity<VehicleResponse> returnVehicle(@PathVariable Long id) throws VehicleNotFoundWithIdException {
        Vehicle vehicle = vehicleService.returnVehicle(id);
        VehicleResponse vehicleResponse =new VehicleResponse();
        vehicleResponse.setStatusCode(201);
        vehicleResponse.setVehicle(vehicle);
        vehicleResponse.setStatusDescription("Vehicle return successfully");
        return new ResponseEntity<>(vehicleResponse, HttpStatus.CREATED);
    }



}
