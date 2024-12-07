package com.example.ClaudeSpringApplication.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "household")
public class Household {
    @Id
    private String eircode;

    @Column(nullable = false)
    private int numberOfOccupants;

    @Column(nullable = false)
    private Integer maxOccupants;

    @Column(nullable = false)
    private Boolean ownerOccupied;


    @JsonManagedReference
    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    public Household(
            @NotBlank(message = "Eircode is required")
            String eircode,
            @Min(value = 1, message = "Number of occupants cannot be negative")
            Integer numberOfOccupants,
            @Min(value = 1, message = "Maximum occupants must be at least 1")
            Integer maxOccupants,
            @NotNull(message = "Owner occupied status is required")
            Boolean ownerOccupied
    ) {
        this.eircode = eircode;
        this.numberOfOccupants = numberOfOccupants;
        this.maxOccupants = maxOccupants;
        this.ownerOccupied = ownerOccupied;
    }

}