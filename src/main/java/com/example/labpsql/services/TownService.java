package com.example.labpsql.services;

import com.example.labpsql.models.Town;

public interface TownService {

    void addTown(String townName);

    Town findTownByName(String townName);
}
