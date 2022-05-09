package com.afikur.blog.controller;

import com.afikur.blog.dto.ApiResponse;
import com.afikur.blog.dto.CategoryRequest;
import com.afikur.blog.dto.PagedResponse;
import com.afikur.blog.mapper.CategoryMapper;
import com.afikur.blog.model.Category;
import com.afikur.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public PagedResponse<Category> findAllCategories(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "30") int size
    ) {
        return categoryService.findAll(page, size);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCategoryById(@PathVariable("id") Long id) {
        return categoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Category update(@RequestBody CategoryRequest categoryRequest, @PathVariable("id") Long id) {
        return categoryService.update(categoryRequest, id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Category add(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category newCategory = categoryMapper.toCategory(categoryRequest);
        return categoryService.save(newCategory);
    }
}
