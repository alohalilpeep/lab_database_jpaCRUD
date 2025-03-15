package com.example.labpsql.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.labpsql.models.Product;
import com.example.labpsql.models.Shop;
import com.example.labpsql.services.dto.ProductDto;
import com.example.labpsql.repositories.ProductRepository;
import com.example.labpsql.services.CategoryService;
import com.example.labpsql.services.ProductService;
import com.example.labpsql.services.ShopService;
import com.example.labpsql.util.ValidationUtil;

import jakarta.validation.ConstraintViolation;
import java.util.Arrays;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private ShopService shopService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addProduct(ProductDto productDto) {

        if (!this.validationUtil.isValid(productDto)) {

            this.validationUtil
                    .violations(productDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

            throw new IllegalArgumentException("Illegal arguments!");
        }

        Product product = this.modelMapper.map(productDto, Product.class);
        product.setCategory(categoryService.findByName(productDto.getCategory()));

        this.productRepository.saveAndFlush(product);

    }

    @Override
    public void addProductDistribution(String productName, String[] shopList) {
        Product product = this.productRepository
                .findByName(productName);

        Arrays.stream(shopList)
                .forEach(s -> {
                    Shop shop = this.shopService.findShopByName(s);

                    product.getShops()
                            .add(shop);
                });

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public Product findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setShopService(ShopService shopService) {
        this.shopService = shopService;
    }
}
