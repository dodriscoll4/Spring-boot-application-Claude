// HouseholdService.java
package com.example.ClaudeSpringApplication.services;

import com.example.ClaudeSpringApplication.dtos.HouseholdStatistics;
import com.example.ClaudeSpringApplication.entities.Household;
import java.util.List;

public interface HouseholdService {
    Household createHousehold(Household household);
    List<Household> getAllHouseholds();
    Household getHouseholdByIdNoPets(String eircode);
    Household getHouseholdByIdWithPets(String eircode);
    Household updateHousehold(String eircode, Household householdDetails);
    void deleteHouseholdById(String eircode);
    List<Household> findHouseholdsWithNoPets();
    List<Household> findOwnerOccupiedHouseholds();
    HouseholdStatistics getHouseholdStatistics();
}