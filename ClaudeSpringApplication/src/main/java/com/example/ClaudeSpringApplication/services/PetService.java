package com.example.ClaudeSpringApplication.services;

import com.example.ClaudeSpringApplication.dtos.PetNameBreed;
import com.example.ClaudeSpringApplication.dtos.PetStatistics;
import com.example.ClaudeSpringApplication.entities.Pet;

import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);
    List<Pet> getAllPets();
    Pet getPetById(Long id);
    Pet updatePet(Long id, Pet petDetails);
    void deletePetById(Long id);
    void deletePetsByName(String name);
    List<Pet> findPetsByAnimalType(String animalType);
    List<Pet> findPetsByBreed(String breed);
    List<PetNameBreed> getPetNameBreeds();
    PetStatistics getPetStatistics();
}