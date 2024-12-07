-- Add this to your existing schema.sql
CREATE TABLE IF NOT EXISTS users (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    unlocked BOOLEAN NOT NULL DEFAULT TRUE
    );

CREATE TABLE IF NOT EXISTS household (
                                         eircode VARCHAR(8) PRIMARY KEY,
    number_of_occupants INT NOT NULL,
    max_occupants INT NOT NULL,
    owner_occupied BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS pet (
                                   id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   name VARCHAR(100) NOT NULL,
    animal_type VARCHAR(50) NOT NULL,
    breed VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    household_eircode VARCHAR(8) NOT NULL,
    FOREIGN KEY (household_eircode) REFERENCES household(eircode)
    );