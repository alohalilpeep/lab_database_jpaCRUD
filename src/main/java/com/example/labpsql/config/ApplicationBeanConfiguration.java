package com.example.labpsql.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.validation.Validation;
import jakarta.validation.Validator;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Validator validator(){
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
