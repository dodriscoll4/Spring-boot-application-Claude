package com.example.ClaudeSpringApplication.controllers;

import com.example.ClaudeSpringApplication.dtos.CreateHouseholdInput;
import com.example.ClaudeSpringApplication.entities.Household;
import com.example.ClaudeSpringApplication.entities.Pet;
import com.example.ClaudeSpringApplication.services.HouseholdService;
import com.example.ClaudeSpringApplication.services.PetService;
import com.example.ClaudeSpringApplication.dtos.HouseholdStatistics;
import com.example.ClaudeSpringApplication.dtos.PetStatistics;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {
    private final HouseholdService householdService;
    private final PetService petService;

    public GraphQLController(HouseholdService householdService, PetService petService) {
        this.householdService = householdService;
        this.petService = petService;
    }

    // Queries
    @QueryMapping
    public List<Household> households() {
        return householdService.getAllHouseholds();
    }

    @QueryMapping
    public List<Pet> petsByAnimalType(@Argument String animalType) {
        return petService.findPetsByAnimalType(animalType);
    }

    @QueryMapping
    public Household household(@Argument String eircode) {
        return householdService.getHouseholdByIdWithPets(eircode);
    }

    @QueryMapping
    public Pet pet(@Argument Long id) {
        return petService.getPetById(id);
    }

    @QueryMapping
    public PetStatistics petStatistics() {
        return petService.getPetStatistics();
    }

    @QueryMapping
    public HouseholdStatistics householdStatistics() {
        return householdService.getHouseholdStatistics();
    }

    // Mutations
    @MutationMapping
    public Household createHousehold(@Argument CreateHouseholdInput input) {
        Household household = new Household(
                input.getEircode(),
                input.getNumberOfOccupants(),
                input.getMaxOccupants(),
                input.isOwnerOccupied()
        );
        return householdService.createHousehold(household);
    }

    @MutationMapping
    public boolean deleteHousehold(@Argument String eircode) {
        try {
            householdService.deleteHouseholdById(eircode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @MutationMapping
    public boolean deletePet(@Argument Long id) {
        try {
            petService.deletePetById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
