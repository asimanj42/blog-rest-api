package com.company.springbootblogrestapi.service.impl;

import com.company.springbootblogrestapi.entity.Category;
import com.company.springbootblogrestapi.exception.ResourceNotFoundException;
import com.company.springbootblogrestapi.mapper.CategoryMapper;
import com.company.springbootblogrestapi.payload.CategoryDto;
import com.company.springbootblogrestapi.repository.CategoryRepository;
import com.company.springbootblogrestapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.mapToCategoryEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        return categoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((categoryMapper::mapToCategoryDto)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setId(categoryId);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.mapToCategoryDto(updatedCategory);

    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        categoryRepository.delete(category);
    }
}
