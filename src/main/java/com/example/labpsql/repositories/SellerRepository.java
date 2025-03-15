package com.example.labpsql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.labpsql.models.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

    Seller findByFirstNameAndLastName(String firstName, String lastName);
}
