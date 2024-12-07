/*package com.example.ClaudeSpringApplication;

import com.example.ClaudeSpringApplication.daos.HouseholdRepository;
import com.example.ClaudeSpringApplication.daos.PetRepository;
import com.example.ClaudeSpringApplication.dtos.HouseholdStatistics;
import com.example.ClaudeSpringApplication.dtos.PetNameBreed;
import com.example.ClaudeSpringApplication.dtos.PetStatistics;
import com.example.ClaudeSpringApplication.entities.Household;
import com.example.ClaudeSpringApplication.entities.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    // ==============================
    // PetRepository Tests
    // ==============================
    @Test
    void findByAnimalTypeIgnoreCase_ShouldReturnPets() {
        Household household = new Household("T12AB34", 2, 4, true, new ArrayList<>());
        entityManager.persist(household);

        Pet pet1 = new Pet(null, "Max", "Dog", "Labrador", 5, household);
        Pet pet2 = new Pet(null, "Luna", "Cat", "Persian", 3, household);
        entityManager.persist(pet1);
        entityManager.persist(pet2);

        List<Pet> foundPets = petRepository.findByAnimalTypeIgnoreCase("dog");
        assertThat(foundPets).hasSize(1);
        assertThat(foundPets.get(0).getName()).isEqualTo("Max");
    }

    @Test
    void deleteByNameIgnoreCase_ShouldRemovePets() {
        Household household = new Household("T34CD56", 2, 4, true, new ArrayList<>());
        entityManager.persist(household);

        Pet pet1 = new Pet(null, "Max", "Dog", "Labrador", 5, household);
        Pet pet2 = new Pet(null, "max", "Cat", "Persian", 3, household);
        entityManager.persist(pet1);
        entityManager.persist(pet2);

        petRepository.deleteByNameIgnoreCase("max");
        List<Pet> remainingPets = petRepository.findAll();
        assertThat(remainingPets).isEmpty();
    }

    @Test
    void findByBreedOrderByAgeAsc_ShouldReturnOrderedPets() {
        Household household = new Household("T56EF78", 2, 4, true, new ArrayList<>());
        entityManager.persist(household);

        Pet pet1 = new Pet(null, "Max", "Dog", "Labrador", 5, household);
        Pet pet2 = new Pet(null, "Rocky", "Dog", "Labrador", 3, household);
        entityManager.persist(pet1);
        entityManager.persist(pet2);

        List<Pet> foundPets = petRepository.findByBreedOrderByAgeAsc("Labrador");
        assertThat(foundPets).hasSize(2);
        assertThat(foundPets.get(0).getAge()).isLessThan(foundPets.get(1).getAge());
    }

    @Test
    void findAllPetNameBreeds_ShouldReturnDTOs() {
        Household household = new Household("T78GH90", 2, 4, true, new ArrayList<>());
        entityManager.persist(household);

        Pet pet = new Pet(null, "Max", "Dog", "Labrador", 5, household);
        entityManager.persist(pet);

        List<PetNameBreed> petBreeds = petRepository.findAllPetNameBreeds();
        assertThat(petBreeds).hasSize(1);
        assertThat(petBreeds.get(0).name()).isEqualTo("Max");
        assertThat(petBreeds.get(0).animalType()).isEqualTo("Dog");
        assertThat(petBreeds.get(0).breed()).isEqualTo("Labrador");
    }

    @Test
    void getPetStatistics_ShouldReturnCorrectStatistics() {
        Household household = new Household("T90IJ12", 2, 4, true, new ArrayList<>());
        entityManager.persist(household);

        Pet pet1 = new Pet(null, "Max", "Dog", "Labrador", 5, household);
        Pet pet2 = new Pet(null, "Luna", "Cat", "Persian", 3, household);
        entityManager.persist(pet1);
        entityManager.persist(pet2);

        PetStatistics stats = petRepository.getPetStatistics();
        assertThat(stats.averageAge()).isEqualTo(4.0);
        assertThat(stats.oldestAge()).isEqualTo(5);
        assertThat(stats.totalCount()).isEqualTo(2);
    }

    // ==============================
    // HouseholdRepository Tests
    // ==============================
    @Test
    void findHouseholdsWithNoPets_ShouldReturnEmptyHouseholds() {
        Household house1 = new Household("T23KL45", 2, 4, true, new ArrayList<>());
        Household house2 = new Household("T67MN89", 1, 2, true, new ArrayList<>());
        entityManager.persist(house1);
        entityManager.persist(house2);

        Pet pet = new Pet(null, "Max", "Dog", "Labrador", 5, house1);
        entityManager.persist(pet);

        List<Household> emptyHouseholds = householdRepository.findHouseholdsWithNoPets();
        assertThat(emptyHouseholds).hasSize(1);
        assertThat(emptyHouseholds.get(0).getEircode()).isEqualTo("T67MN89");
    }

    @Test
    void findByOwnerOccupied_ShouldReturnMatchingHouseholds() {
        Household house1 = new Household("T12NO34", 2, 4, true, new ArrayList<>());
        Household house2 = new Household("T56PQ78", 1, 2, false, new ArrayList<>());
        entityManager.persist(house1);
        entityManager.persist(house2);

        List<Household> ownerOccupied = householdRepository.findByOwnerOccupied(true);
        assertThat(ownerOccupied).hasSize(1);
        assertThat(ownerOccupied.get(0).getEircode()).isEqualTo("T12NO34");
    }

    @Test
    void getHouseholdStatistics_ShouldReturnCorrectStatistics() {
        Household house1 = new Household("T90RS12", 0, 4, true, new ArrayList<>());
        Household house2 = new Household("T34UV56", 2, 2, true, new ArrayList<>());
        entityManager.persist(house1);
        entityManager.persist(house2);

        HouseholdStatistics stats = householdRepository.getHouseholdStatistics();
        assertThat(stats.emptyHouses()).isEqualTo(1);
        assertThat(stats.fullHouses()).isEqualTo(1);
    }
}
*/