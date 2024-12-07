// HouseholdServiceImpl.java
package com.example.ClaudeSpringApplication.services;

import com.example.ClaudeSpringApplication.daos.HouseholdRepository;
import com.example.ClaudeSpringApplication.dtos.HouseholdStatistics;
import com.example.ClaudeSpringApplication.entities.Household;
import com.example.ClaudeSpringApplication.services.exceptions.InvalidDataException;
import com.example.ClaudeSpringApplication.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseholdServiceImpl implements HouseholdService {
    private final HouseholdRepository householdRepository;

    @Override
    public Household createHousehold(Household household) {
        if (household.getNumberOfOccupants() > household.getMaxOccupants()) {
            throw new InvalidDataException("Number of occupants cannot exceed maximum occupants");
        }
        return householdRepository.save(household);
    }

    @Override
    public List<Household> getAllHouseholds() {
        return householdRepository.findAll();
    }

    @Override
    public Household getHouseholdByIdNoPets(String eircode) {
        return householdRepository.findById(eircode)
                .orElseThrow(() -> new ResourceNotFoundException("Household not found with eircode: " + eircode));
    }

    @Override
    @Transactional(readOnly = true)
    public Household getHouseholdByIdWithPets(String eircode) {
        Household household = getHouseholdByIdNoPets(eircode);
        household.getPets().size(); // Force initialization of pets
        return household;
    }

    @Override
    public Household updateHousehold(String eircode, Household householdDetails) {
        Household household = getHouseholdByIdNoPets(eircode);
        if (householdDetails.getNumberOfOccupants() > householdDetails.getMaxOccupants()) {
            throw new InvalidDataException("Number of occupants cannot exceed maximum occupants");
        }
        household.setNumberOfOccupants(householdDetails.getNumberOfOccupants());
        household.setMaxOccupants(householdDetails.getMaxOccupants());
        household.setOwnerOccupied(householdDetails.getOwnerOccupied());
        return householdRepository.save(household);
    }

    @Override
    @Transactional
    public void deleteHouseholdById(String eircode) {
        if (!householdRepository.existsById(eircode)) {
            throw new ResourceNotFoundException("Household not found with eircode: " + eircode);
        }
        householdRepository.deleteById(eircode);
    }

    @Override
    public List<Household> findHouseholdsWithNoPets() {
        return householdRepository.findHouseholdsWithNoPets();
    }

    @Override
    public List<Household> findOwnerOccupiedHouseholds() {
        return householdRepository.findByOwnerOccupied(true);
    }

    @Override
    public HouseholdStatistics getHouseholdStatistics() {
        return householdRepository.getHouseholdStatistics();
    }
}