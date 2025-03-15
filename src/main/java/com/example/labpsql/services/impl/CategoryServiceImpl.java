package com.example.labpsql.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.labpsql.models.Category;
import com.example.labpsql.services.dto.CategoryDto;
import com.example.labpsql.repositories.CategoryRepository;
import com.example.labpsql.services.CategoryService;
import com.example.labpsql.util.ValidationUtil;

import jakarta.validation.ConstraintViolation;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCategory(String name) {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(name);

        if (!this.validationUtil.isValid(categoryDto)) {
            this.validationUtil
                    .violations(categoryDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                this.categoryRepository
                        .saveAndFlush(this.modelMapper.map(categoryDto, Category.class));
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }
    }

    @Override
    public Category findByName(String name) {

        return this.categoryRepository.findByName(name);
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
