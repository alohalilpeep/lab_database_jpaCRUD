package com.example.labpsql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.labpsql.models.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, String> {

    Shop findByName(String name);

}
