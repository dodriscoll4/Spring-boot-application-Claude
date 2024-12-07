package com.example.ClaudeSpringApplication.controllers;

import com.example.ClaudeSpringApplication.daos.HouseholdRepository;
import com.example.ClaudeSpringApplication.dtos.*;
import com.example.ClaudeSpringApplication.entities.Household;
import com.example.ClaudeSpringApplication.services.HouseholdService;
import com.example.ClaudeSpringApplication.services.PetService;
import com.example.ClaudeSpringApplication.entities.Pet;
import com.example.ClaudeSpringApplication.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    private final PetService petService;
    private final HouseholdRepository householdRepository;

    public PetController(PetService petService, HouseholdRepository householdRepository) {
        this.petService = petService;
        this.householdRepository = householdRepository;

    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody CreatePetDTO petDTO) {
        // Fetch household and associate with the pet
        Household household = householdRepository.findById(petDTO.householdEircode())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Household not found with eircode: " + petDTO.householdEircode()));

        Pet pet = new Pet(
                petDTO.name(),
                petDTO.animalType(),
                petDTO.breed(),
                petDTO.age(),
                household
        );

        return ResponseEntity.ok(petService.createPet(pet));
    }



    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id,
                                         @Valid @RequestBody UpdatePetDTO petDTO) {
        Pet pet = new Pet(
                petDTO.name(),
                petDTO.animalType(),
                petDTO.breed(),
                petDTO.age(),
                null // household eircode is not updated
        );
        return ResponseEntity.ok(petService.updatePet(id, pet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deletePetsByName(@PathVariable String name) {
        petService.deletePetsByName(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{animalType}")
    public ResponseEntity<List<Pet>> getPetsByType(@PathVariable String animalType) {
        return ResponseEntity.ok(petService.findPetsByAnimalType(animalType));
    }

    @GetMapping("/breed/{breed}")
    public ResponseEntity<List<Pet>> getPetsByBreed(@PathVariable String breed) {
        return ResponseEntity.ok(petService.findPetsByBreed(breed));
    }

    @GetMapping("/namebreeds")
    public ResponseEntity<List<PetNameBreed>> getPetNameBreeds() {
        return ResponseEntity.ok(petService.getPetNameBreeds());
    }

    @GetMapping("/statistics")
    public ResponseEntity<PetStatistics> getPetStatistics() {
        return ResponseEntity.ok(petService.getPetStatistics());
    }
}
