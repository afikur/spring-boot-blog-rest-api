package com.afikur.blog.mapper.impl;

import com.afikur.blog.dto.CategoryRequest;
import com.afikur.blog.mapper.CategoryMapper;
import com.afikur.blog.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public Category toCategory(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            return null;
        }
        Category category = new Category();
        category.setName(categoryRequest.name());
        return category;
    }
}
