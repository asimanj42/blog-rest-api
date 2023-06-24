package com.company.springbootblogrestapi.mapper;

import com.company.springbootblogrestapi.entity.Category;
import com.company.springbootblogrestapi.payload.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper modelMapper;

    public CategoryDto mapToCategoryDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    public Category mapToCategoryEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
