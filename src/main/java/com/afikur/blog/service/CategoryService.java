package com.afikur.blog.service;

import com.afikur.blog.dto.ApiResponse;
import com.afikur.blog.dto.CategoryRequest;
import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.model.Category;

public interface CategoryService {
    PagedResponse<Category> findAll(int pageNumber, int size);

    Category save(Category category);

    Category findById(Long id);

    ApiResponse deleteById(Long id);

    Category update(CategoryRequest category, Long id);
}
