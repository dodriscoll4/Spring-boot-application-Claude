package com.example.ClaudeSpringApplication.dtos;

import jakarta.validation.constraints.*;

public record CreateHouseholdDTO(
        @NotBlank(message = "Eircode is required")
        String eircode,

        @NotNull(message = "Number of occupants is required")
        @Min(value = 0, message = "Number of occupants cannot be negative")
        Integer numberOfOccupants,

        @NotNull(message = "Max occupants is required")
        @Min(value = 1, message = "Max occupants must be at least 1")
        Integer maxOccupants,

        @NotNull(message = "Owner occupied status is required")
        Boolean ownerOccupied
) {}
