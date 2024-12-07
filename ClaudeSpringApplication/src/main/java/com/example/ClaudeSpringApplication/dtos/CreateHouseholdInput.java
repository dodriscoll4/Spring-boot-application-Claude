package com.example.ClaudeSpringApplication.dtos;

// Input class for createHousehold mutation
public class CreateHouseholdInput {
    private String eircode;
    private int numberOfOccupants;
    private int maxOccupants;
    private boolean ownerOccupied;

    // Getters and setters
    public String getEircode() { return eircode; }
    public void setEircode(String eircode) { this.eircode = eircode; }
    public int getNumberOfOccupants() { return numberOfOccupants; }
    public void setNumberOfOccupants(int numberOfOccupants) { this.numberOfOccupants = numberOfOccupants; }
    public int getMaxOccupants() { return maxOccupants; }
    public void setMaxOccupants(int maxOccupants) { this.maxOccupants = maxOccupants; }
    public boolean isOwnerOccupied() { return ownerOccupied; }
    public void setOwnerOccupied(boolean ownerOccupied) { this.ownerOccupied = ownerOccupied; }
}