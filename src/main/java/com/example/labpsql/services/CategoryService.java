package com.example.labpsql.services;

import com.example.labpsql.models.Category;

public interface CategoryService {

    void addCategory(String name);

    Category findByName(String name);
}
