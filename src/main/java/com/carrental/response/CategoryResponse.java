package com.carrental.response;

import com.carrental.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse extends BaseResponse{
    private Category category;
    private Iterable<Category> categoryList;
}
