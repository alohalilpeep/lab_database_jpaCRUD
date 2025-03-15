package com.example.labpsql.services.impl;

import com.example.labpsql.repositories.TownRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.labpsql.models.Shop;
import com.example.labpsql.services.dto.ShopDto;
import com.example.labpsql.views.ProductViewModel;
import com.example.labpsql.views.SellerViewModel;
import com.example.labpsql.repositories.ShopRepository;
import com.example.labpsql.services.ShopService;
import com.example.labpsql.services.TownService;
import com.example.labpsql.util.ValidationUtil;

import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository shopRepository;
    private TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public ShopServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addShop(ShopDto shopDto) {

        if (!this.validationUtil.isValid(shopDto)) {

            this.validationUtil
                    .violations(shopDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        Shop shop = this.modelMapper.map(shopDto, Shop.class);
        shop.setTown(this.townService.findTownByName(shopDto.getTown()));

        this.shopRepository
                .saveAndFlush(shop);
    }

    @Override
    public Shop findShopByName(String name) {

        return this.shopRepository.findByName(name);
    }

    @Override
    public List<SellerViewModel> findAllSellersFromShop(String shopName) {
        return this.shopRepository
                .findByName(shopName)
                .getSellers()
                .stream()
                .map(seller -> this.modelMapper.map(seller, SellerViewModel.class))
                .collect(Collectors.toList());

    }

    @Override
    public List<ProductViewModel> findAllProductsFromShop(String shopName) {

        return this.shopRepository
                .findByName(shopName)
                .getProducts()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Autowired
    public void setShopRepository(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }


    @Autowired
    public void setTownService(TownService townService) {
        this.townService = townService;
    }
}
