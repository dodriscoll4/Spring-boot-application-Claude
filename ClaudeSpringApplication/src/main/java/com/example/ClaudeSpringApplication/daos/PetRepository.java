// PetRepository.java
package com.example.ClaudeSpringApplication.daos;


import com.example.ClaudeSpringApplication.dtos.PetNameBreed;
import com.example.ClaudeSpringApplication.dtos.PetStatistics;
import com.example.ClaudeSpringApplication.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    void deleteByNameIgnoreCase(String name);

    List<Pet> findByAnimalTypeIgnoreCase(String animalType);

    List<Pet> findByBreedOrderByAgeAsc(String breed);

    @Query("SELECT new com.example.ClaudeSpringApplication.dtos.PetNameBreed(p.name, p.animalType, p.breed) FROM Pet p")
    List<PetNameBreed> findAllPetNameBreeds();

    @Query("SELECT new com.example.ClaudeSpringApplication.dtos.PetStatistics(AVG(p.age), MAX(p.age), COUNT(p)) FROM Pet p")
    PetStatistics getPetStatistics();
}
