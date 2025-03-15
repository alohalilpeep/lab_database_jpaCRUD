package com.example.labpsql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.labpsql.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String>{
    Category findByName(String name);
}
