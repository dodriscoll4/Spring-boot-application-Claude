package com.example.ClaudeSpringApplication;


import com.example.ClaudeSpringApplication.daos.HouseholdRepository;
import com.example.ClaudeSpringApplication.daos.PetRepository;
import com.example.ClaudeSpringApplication.dtos.HouseholdStatistics;
import com.example.ClaudeSpringApplication.dtos.PetStatistics;
import com.example.ClaudeSpringApplication.entities.Household;
import com.example.ClaudeSpringApplication.entities.Pet;
import com.example.ClaudeSpringApplication.services.HouseholdServiceImpl;
import com.example.ClaudeSpringApplication.services.PetServiceImpl;
import com.example.ClaudeSpringApplication.services.exceptions.InvalidDataException;
import com.example.ClaudeSpringApplication.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetServiceImpl petService;

    private Pet pet;
    private Household household;
/*
    @BeforeEach
    void setUp() {
        household = new Household("D04X4H2", 2, 4, true, null);
        pet = new Pet(1L, "Max", "Dog", "Labrador", 5, household);
    }
*/
    @Test
    void createPet_WithValidData_ShouldSucceed() {
        when(petRepository.save(any(Pet.class))).thenReturn(pet);
        Pet savedPet = petService.createPet(pet);
        assertThat(savedPet).isEqualTo(pet);
    }

    @Test
    void createPet_WithNegativeAge_ShouldThrowException() {
        pet.setAge(-1);
        assertThrows(InvalidDataException.class, () -> petService.createPet(pet));
    }

    @Test
    void getPetById_WhenExists_ShouldReturnPet() {
        when(petRepository.findById(1L)).thenReturn(Optional.of(pet));
        Pet foundPet = petService.getPetById(1L);
        assertThat(foundPet).isEqualTo(pet);
    }

    @Test
    void getPetById_WhenNotExists_ShouldThrowException() {
        when(petRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> petService.getPetById(1L));
    }

    @Test
    void getPetStatistics_ShouldReturnStatistics() {
        PetStatistics stats = new PetStatistics(4.5, 10, 5);
        when(petRepository.getPetStatistics()).thenReturn(stats);
        PetStatistics result = petService.getPetStatistics();
        assertThat(result).isEqualTo(stats);
    }
}

@ExtendWith(MockitoExtension.class)
class HouseholdServiceTest {
    @Mock
    private HouseholdRepository householdRepository;

    @InjectMocks
    private HouseholdServiceImpl householdService;

    private Household household;

    @BeforeEach
    void setUp() {
        household = new Household("D04X4H2", 2, 4, true, null);
    }

    @Test
    void createHousehold_WithValidData_ShouldSucceed() {
        when(householdRepository.save(any(Household.class))).thenReturn(household);
        Household savedHousehold = householdService.createHousehold(household);
        assertThat(savedHousehold).isEqualTo(household);
    }

    @Test
    void createHousehold_WithInvalidOccupants_ShouldThrowException() {
        household.setNumberOfOccupants(5);
        household.setMaxOccupants(4);
        assertThrows(InvalidDataException.class, () -> householdService.createHousehold(household));
    }

    @Test
    void getHouseholdByIdNoPets_WhenExists_ShouldReturnHousehold() {
        when(householdRepository.findById("D04X4H2")).thenReturn(Optional.of(household));
        Household found = householdService.getHouseholdByIdNoPets("D04X4H2");
        assertThat(found).isEqualTo(household);
    }

    @Test
    void getHouseholdByIdNoPets_WhenNotExists_ShouldThrowException() {
        when(householdRepository.findById("D04X4H2")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> householdService.getHouseholdByIdNoPets("D04X4H2"));
    }

    @Test
    void findHouseholdsWithNoPets_ShouldReturnList() {
        List<Household> emptyHouseholds = List.of(household);
        when(householdRepository.findHouseholdsWithNoPets()).thenReturn(emptyHouseholds);
        List<Household> result = householdService.findHouseholdsWithNoPets();
        assertThat(result).isEqualTo(emptyHouseholds);
    }

    @Test
    void getHouseholdStatistics_ShouldReturnStatistics() {
        HouseholdStatistics stats = new HouseholdStatistics(2, 3);
        when(householdRepository.getHouseholdStatistics()).thenReturn(stats);
        HouseholdStatistics result = householdService.getHouseholdStatistics();
        assertThat(result).isEqualTo(stats);
    }
}