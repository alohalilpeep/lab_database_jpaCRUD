package com.example.labpsql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.labpsql.models.Town;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, String> {

    Optional<Town> findByName(String name);
}
