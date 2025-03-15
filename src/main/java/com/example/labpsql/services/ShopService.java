package com.example.labpsql.services;

import com.example.labpsql.models.Shop;
import com.example.labpsql.services.dto.ShopDto;
import com.example.labpsql.views.ProductViewModel;
import com.example.labpsql.views.SellerViewModel;

import java.util.List;

public interface ShopService {

    void addShop(ShopDto shopDto);

    Shop findShopByName(String name);

    List<SellerViewModel> findAllSellersFromShop(String shopName);

    List<ProductViewModel> findAllProductsFromShop(String shopName);
}
