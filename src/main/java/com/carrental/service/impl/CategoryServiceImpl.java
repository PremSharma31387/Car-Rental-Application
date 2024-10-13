package com.carrental.service.impl;

import com.carrental.entity.Category;
import com.carrental.exception.category.CategoryAlreadyExistWIthIdException;
import com.carrental.exception.category.CategoryNotFoundWithThisNameException;
import com.carrental.exception.category.CategoryNotFoundWithIdException;
import com.carrental.repository.CategoryRepository;
import com.carrental.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) throws CategoryNotFoundWithIdException {
        Optional<Category> categoryById = categoryRepository.findById(id);
        if(categoryById.isEmpty()){
            throw new CategoryNotFoundWithIdException();
        }
        return categoryById.get();
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = categoryRepository.findCategoryByCategoryName(name.toUpperCase());
        if(category==null){
            throw new CategoryNotFoundWithThisNameException();

        }
        return category;
    }

    @Override
    public Category createCategory(Category category) {
        System.out.println(category);
        category.setCategoryName(category.getCategoryName().toUpperCase());
        Category categoryByName = categoryRepository.findCategoryByCategoryName(category.getCategoryName());
        if(categoryByName!=null){
            throw new CategoryAlreadyExistWIthIdException();
        }
        return categoryRepository.save(category);
    }



    @Override
    public Category updateCategory(Long id, Category category) {
        Category categoryById = getCategoryById(id);
        categoryById.setCategoryName(category.getCategoryName());
        categoryById.setCategoryLevel(category.getCategoryLevel());
        categoryById.setVehicleList(category.getVehicleList());
        categoryById.setDiscountPercent(category.getDiscountPercent());
        return categoryRepository.save(categoryById);

    }

    @Override
    public void deleteCategory(Long id) {
        Category categoryById = getCategoryById(id);
        categoryRepository.delete(categoryById);
    }
}
