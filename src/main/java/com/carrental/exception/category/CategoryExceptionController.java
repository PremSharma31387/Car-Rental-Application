package com.carrental.exception.category;

import com.carrental.response.CategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryExceptionController {

    @ExceptionHandler(value = CategoryNotFoundWithIdException.class)
    public ResponseEntity<CategoryResponse> exception(CategoryNotFoundWithIdException e){
        CategoryResponse categoryResponse  = new CategoryResponse();
        categoryResponse.setStatusCode(404);
        categoryResponse.setStatusDescription("Category is not present with this id");
        return new ResponseEntity<>(categoryResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CategoryNotFoundWithThisNameException.class)
    public ResponseEntity<CategoryResponse> exception(CategoryNotFoundWithThisNameException e){
        CategoryResponse categoryResponse  = new CategoryResponse();
        categoryResponse.setStatusCode(404);
        categoryResponse.setStatusDescription("Category is not found with this name");
        return new ResponseEntity<>(categoryResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = CategoryAlreadyExistWIthIdException.class)
    public ResponseEntity<CategoryResponse> exception(CategoryAlreadyExistWIthIdException e){
        CategoryResponse categoryResponse  = new CategoryResponse();
        categoryResponse.setStatusCode(409);
        categoryResponse.setStatusDescription("Category is already with this id");
        return new ResponseEntity<>(categoryResponse, HttpStatus.CONFLICT);
    }
}
