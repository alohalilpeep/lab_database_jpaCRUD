package com.example.labpsql.services;

import com.example.labpsql.models.Product;
import com.example.labpsql.services.dto.ProductDto;

public interface ProductService {

    void addProduct(ProductDto productDto);

    void addProductDistribution(String productName, String[] shopList);

    Product findByName(String name);
}
