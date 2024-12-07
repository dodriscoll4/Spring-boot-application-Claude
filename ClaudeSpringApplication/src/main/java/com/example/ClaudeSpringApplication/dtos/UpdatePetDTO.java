package com.example.ClaudeSpringApplication.dtos;

import jakarta.validation.constraints.*;

public record UpdatePetDTO(
        @NotBlank(message = "Pet name is required")
        @Size(min = 2, max = 100, message = "Pet name must be between 2 and 100 characters")
        String name,

        @NotBlank(message = "Animal type is required")
        @Size(min = 2, max = 50, message = "Animal type must be between 2 and 50 characters")
        String animalType,

        @NotBlank(message = "Breed is required")
        @Size(min = 2, max = 100, message = "Breed must be between 2 and 100 characters")
        String breed,

        @Min(value = 0, message = "Age cannot be negative")
        @Max(value = 30, message = "Age cannot be more than 30")
        Integer age
) {}