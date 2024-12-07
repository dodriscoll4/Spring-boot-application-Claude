package com.example.ClaudeSpringApplication.controllers;

import com.example.ClaudeSpringApplication.dtos.*;
import com.example.ClaudeSpringApplication.entities.Household;
import com.example.ClaudeSpringApplication.services.HouseholdService;
import com.example.ClaudeSpringApplication.services.PetService;
import com.example.ClaudeSpringApplication.entities.Pet;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/households")
public class HouseholdController {
    private final HouseholdService householdService;

    public HouseholdController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@Valid @RequestBody CreateHouseholdDTO dto) {
        Household household = new Household(
                dto.eircode(),
                dto.numberOfOccupants(),
                dto.maxOccupants(),
                dto.ownerOccupied()
        );
        return ResponseEntity.ok(householdService.createHousehold(household));
    }

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return ResponseEntity.ok(householdService.getAllHouseholds());
    }

    @GetMapping("/{eircode}")
    public ResponseEntity<Household> getHouseholdByEircode(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.getHouseholdByIdNoPets(eircode));
    }

    @GetMapping("/{eircode}/with-pets")
    public ResponseEntity<Household> getHouseholdWithPets(@PathVariable String eircode) {
        return ResponseEntity.ok(householdService.getHouseholdByIdWithPets(eircode));
    }

    @PutMapping("/{eircode}")
    public ResponseEntity<Household> updateHousehold(
            @PathVariable String eircode,
            @Valid @RequestBody UpdateHouseholdDTO dto) {
        Household household = new Household(
                eircode,
                dto.numberOfOccupants(),
                dto.maxOccupants(),
                dto.ownerOccupied()
        );
        return ResponseEntity.ok(householdService.updateHousehold(eircode, household));
    }

    @DeleteMapping("/{eircode}")
    public ResponseEntity<Void> deleteHousehold(@PathVariable String eircode) {
        householdService.deleteHouseholdById(eircode);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/no-pets")
    public ResponseEntity<List<Household>> getHouseholdsWithNoPets() {
        return ResponseEntity.ok(householdService.findHouseholdsWithNoPets());
    }

    @GetMapping("/owner-occupied")
    public ResponseEntity<List<Household>> getOwnerOccupiedHouseholds() {
        return ResponseEntity.ok(householdService.findOwnerOccupiedHouseholds());
    }

    @GetMapping("/statistics")
    public ResponseEntity<HouseholdStatistics> getHouseholdStatistics() {
        return ResponseEntity.ok(householdService.getHouseholdStatistics());
    }
}
