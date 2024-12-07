package com.example.ClaudeSpringApplication.dtos;

import jakarta.validation.constraints.*;

public record UpdateHouseholdDTO(
        @Min(value = 0, message = "Number of occupants cannot be negative")
        Integer numberOfOccupants,

        @Min(value = 1, message = "Maximum occupants must be at least 1")
        Integer maxOccupants,

        @NotNull(message = "Owner occupied status is required")
        Boolean ownerOccupied
) {}