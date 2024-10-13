package com.carrental.controller;

import com.carrental.entity.Category;
import com.carrental.exception.category.CategoryNotFoundWithIdException;
import com.carrental.response.CategoryResponse;
import com.carrental.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryResponse> getAllCategory(){
        Iterable<Category> allCategory = categoryService.getAllCategory();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setStatusCode(200);
        categoryResponse.setStatusDescription("Get all category successfully");
        categoryResponse.setCategoryList(allCategory);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id){
        CategoryResponse categoryResponse = new CategoryResponse();
        Category category = categoryService.getCategoryById(id);
        System.out.println(category);
        categoryResponse.setCategory(category);
        categoryResponse.setStatusDescription("Category is present with this id");
        categoryResponse.setStatusCode(200);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<CategoryResponse> getCategoryByName(@PathVariable String name){
        CategoryResponse categoryResponse = new CategoryResponse();
        Category category = categoryService.getCategoryByName(name);
        System.out.println(category);
        categoryResponse.setCategory(category);
        categoryResponse.setStatusDescription("Category is present with this name");
        categoryResponse.setStatusCode(200);
        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }



    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody Category category){
        Category categoryCreated = categoryService.createCategory(category);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setStatusCode(201);
        categoryResponse.setStatusDescription("Category created successfully");
        categoryResponse.setCategory(categoryCreated);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id,@RequestBody Category category){
        Category categoryUpdated = categoryService.updateCategory(id,category);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setStatusCode(201);
        categoryResponse.setStatusDescription("Category updated successfully");
        categoryResponse.setCategory(categoryUpdated);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setStatusCode(200);
        categoryResponse.setStatusDescription("Category deleted successfully");
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }



}
