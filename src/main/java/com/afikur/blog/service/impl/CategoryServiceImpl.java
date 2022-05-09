package com.afikur.blog.service.impl;

import com.afikur.blog.dto.ApiResponse;
import com.afikur.blog.dto.CategoryRequest;
import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.exception.ResourceNotFoundException;
import com.afikur.blog.model.Category;
import com.afikur.blog.repository.CategoryRepository;
import com.afikur.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public PagedResponse<Category> findAll(int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.Direction.DESC, "createdAt");
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<Category> categories = categoryPage.getTotalElements() == 0 ?
                Collections.emptyList() : categoryPage.getContent();

        return new PagedResponse<>(categories, categoryPage.getNumber(), categoryPage.getSize(),
                categoryPage.getTotalElements(), categoryPage.getTotalPages(), categoryPage.isLast());
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
    }

    @Override
    public ApiResponse deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        categoryRepository.delete(category);
        return new ApiResponse(Boolean.TRUE, "Category has been deleted successfully");
    }

    @Override
    public Category update(CategoryRequest newCategory, Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

        category.setName(newCategory.name());
        return categoryRepository.save(category);
    }
}
