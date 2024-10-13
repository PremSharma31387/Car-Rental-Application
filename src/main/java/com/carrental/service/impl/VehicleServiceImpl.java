package com.carrental.service.impl;

import com.carrental.entity.Category;
import com.carrental.entity.Vehicle;
import com.carrental.exception.vehicle.VehicleNotFoundWithIdException;
import com.carrental.repository.CategoryRepository;
import com.carrental.repository.VehicleRepository;
import com.carrental.service.CategoryService;
import com.carrental.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    //    Get All Vehicle Service
    @Override
    public Iterable<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

//    Create Vehicle Service
    @Override
    public Vehicle createVehicle(Vehicle vehicle) throws VehicleNotFoundWithIdException {
        categoryService.getCategoryById(vehicle.getCategoryId());
        return vehicleRepository.save(vehicle);
    }

    //    Get Vehicle By Id Service
    @Override
    public Vehicle getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if(vehicle.isEmpty()){
            throw new VehicleNotFoundWithIdException();
        }
        return vehicle.get();
    }

//    Get Vehicle By CategoryId Service
    @Override
    public Iterable<Vehicle> getVehicleListByCategoryId(Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return category.getVehicleList();
    }

    @Override
    public Iterable<Vehicle> getVehicleListByCategoryName(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        return category.getVehicleList();
    }


    //    Update Vehicle Service
    @Override
    public Vehicle updateVehicle(Long id, Vehicle vehicle) throws VehicleNotFoundWithIdException {
        Vehicle vehicleById = getVehicleById(id);
        vehicleById.setName(vehicle.getName());
        vehicleById.setModel(vehicle.getModel());
        vehicleById.setCategoryId(vehicle.getCategoryId());
        vehicleById.setAvailable(vehicle.isAvailable());
        vehicleById.setPricePerDay(vehicle.getPricePerDay());
        return vehicleRepository.save(vehicleById);
    }

//    Delete Vehicle Service
    @Override
    public void deleteVehicle(Long id) throws VehicleNotFoundWithIdException {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }

    @Override
    public Vehicle returnVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setAvailable(true);
       return vehicleRepository.save(vehicle);
    }
}
