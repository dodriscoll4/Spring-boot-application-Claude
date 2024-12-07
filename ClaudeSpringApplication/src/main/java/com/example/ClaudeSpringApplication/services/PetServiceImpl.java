// PetServiceImpl.java
package com.example.ClaudeSpringApplication.services;


import com.example.ClaudeSpringApplication.daos.HouseholdRepository;
import com.example.ClaudeSpringApplication.daos.PetRepository;
import com.example.ClaudeSpringApplication.dtos.PetNameBreed;
import com.example.ClaudeSpringApplication.dtos.PetStatistics;
import com.example.ClaudeSpringApplication.entities.Household;
import com.example.ClaudeSpringApplication.entities.Pet;
import com.example.ClaudeSpringApplication.services.exceptions.InvalidDataException;
import com.example.ClaudeSpringApplication.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final HouseholdRepository householdRepository;

    @Override
    public Pet createPet(Pet pet) {
        // Validate household eircode before saving the pet
        String householdEircode = pet.getHousehold().getEircode();
        Household household = householdRepository.findById(householdEircode)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Household not found with eircode: " + householdEircode));

        // Set the household in the pet entity
        pet.setHousehold(household);

        // Save and return the pet
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet not found with id: " + id));
    }

    @Override
    public Pet updatePet(Long id, Pet petDetails) {
        // Fetch existing pet and validate
        Pet pet = getPetById(id);
        if (petDetails.getAge() < 0) {
            throw new InvalidDataException("Pet age cannot be negative");
        }

        // Update pet details
        pet.setName(petDetails.getName());
        pet.setAnimalType(petDetails.getAnimalType());
        pet.setBreed(petDetails.getBreed());
        pet.setAge(petDetails.getAge());

        // Save and return updated pet
        return petRepository.save(pet);
    }

    @Override
    public void deletePetById(Long id) {
        if (!petRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pet not found with id: " + id);
        }
        petRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deletePetsByName(String name) {
        petRepository.deleteByNameIgnoreCase(name);
    }

    @Override
    public List<Pet> findPetsByAnimalType(String animalType) {
        return petRepository.findByAnimalTypeIgnoreCase(animalType);
    }

    @Override
    public List<Pet> findPetsByBreed(String breed) {
        return petRepository.findByBreedOrderByAgeAsc(breed);
    }

    @Override
    public List<PetNameBreed> getPetNameBreeds() {
        return petRepository.findAllPetNameBreeds();
    }

    @Override
    public PetStatistics getPetStatistics() {
        return petRepository.getPetStatistics();
    }
}