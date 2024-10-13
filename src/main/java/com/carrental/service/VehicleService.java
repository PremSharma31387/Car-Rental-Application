package com.carrental.service;

import com.carrental.entity.Vehicle;
import com.carrental.exception.vehicle.VehicleNotFoundWithIdException;

import java.util.List;

public interface VehicleService {
    public List<Vehicle> getAllVehicle();
    public Vehicle createVehicle(Vehicle vehicle) throws VehicleNotFoundWithIdException;
    public Vehicle getVehicleById(Long id);
    public Iterable<Vehicle> getVehicleListByCategoryId(Long categoryId);
    public Iterable<Vehicle> getVehicleListByCategoryName(String categoryName);
    public Vehicle updateVehicle(Long id, Vehicle vehicle) throws VehicleNotFoundWithIdException;
    public void deleteVehicle(Long id) throws VehicleNotFoundWithIdException;
    public Vehicle returnVehicle(Long id);
    public void returnAllVehicle();
}
