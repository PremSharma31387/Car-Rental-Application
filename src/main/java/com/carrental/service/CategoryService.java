package com.carrental.service;

import com.carrental.entity.Category;
import com.carrental.exception.category.CategoryNotFoundWithIdException;

public interface CategoryService {
    public Iterable<Category> getAllCategory();
    public Category getCategoryById(Long id) throws CategoryNotFoundWithIdException;
    public Category getCategoryByName(String name);
    public Category createCategory(Category category);
    public Category updateCategory(Long id,Category category);
    public void deleteCategory(Long id);

}
