package com.afikur.blog.mapper;

import com.afikur.blog.dto.CategoryRequest;
import com.afikur.blog.model.Category;

public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);
}
