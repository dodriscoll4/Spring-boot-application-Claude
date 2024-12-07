// HouseholdRepository.java
package com.example.ClaudeSpringApplication.daos;


import com.example.ClaudeSpringApplication.dtos.HouseholdStatistics;
import com.example.ClaudeSpringApplication.entities.Household;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, String> {
    @Query("SELECT h FROM Household h WHERE h.pets IS EMPTY")
    List<Household> findHouseholdsWithNoPets();

    List<Household> findByOwnerOccupied(Boolean ownerOccupied);

    @Query("SELECT new com.example.ClaudeSpringApplication.dtos.HouseholdStatistics(" +
            "COUNT(CASE WHEN h.numberOfOccupants = 0 THEN 1 END), " +
            "COUNT(CASE WHEN h.numberOfOccupants = h.maxOccupants THEN 1 END)) " +
            "FROM Household h")
    HouseholdStatistics getHouseholdStatistics();

    @EntityGraph(attributePaths = {"pets"})
    Optional<Household> findByEircode(String eircode);
}