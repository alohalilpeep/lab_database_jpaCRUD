package com.example.labpsql.services;

import com.example.labpsql.services.dto.SellerDto;

public interface SellerService {

    void addSeller(SellerDto sellerDto);

    void addManager(String[] sellerParams, String[] managerParams);
}
