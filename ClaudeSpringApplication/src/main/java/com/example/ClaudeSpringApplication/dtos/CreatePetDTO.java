package com.example.ClaudeSpringApplication.dtos;

import jakarta.validation.constraints.*;

public record CreatePetDTO(
        @NotBlank(message = "Pet name is required")
        String name,

        @NotBlank(message = "Animal type is required")
        String animalType,

        @NotBlank(message = "Breed is required")
        String breed,

        @NotNull(message = "Age is required")
        @Min(value = 0, message = "Age cannot be negative")
        Integer age,

        @NotBlank(message = "Household eircode is required")
        String householdEircode
) {}