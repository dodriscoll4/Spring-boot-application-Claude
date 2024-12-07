-- Add this to your existing data.sql
-- Password is 'admin123' encoded with BCrypt
-- Add this to your existing data.sql
-- Password is 'admin123' encoded with BCrypt
INSERT INTO users (username, password, role, unlocked) VALUES
    ('admin', '$2a$10$N/qM0TbiVFg/yqeRZsqGiOUUwVe3zTMFM0ZV3krHDjHgYZiDtOEJi', 'ROLE_ADMIN', true);
-- Password is 'user123' encoded with BCrypt
INSERT INTO users (username, password, role, unlocked) VALUES
    ('user', '$2a$10$TPRcgDgPZscHiZVOvJ8mGuTWTZKAZWXMyVz7EUZOa0YGM6TJpMxXy', 'ROLE_USER', true);

INSERT INTO household (eircode, number_of_occupants, max_occupants, owner_occupied) VALUES
                                                                                                 ('D04X4H2', 2, 4, true),
                                                                                                 ('D06W3P1', 3, 3, true),
                                                                                                 ('D08Y6N4', 1, 2, false),
                                                                                                 ('D02R6K8',2, 4, true),
                                                                                                 ('D01T2M5',0, 2, true);

INSERT INTO pet (name, animal_type, breed, age, household_eircode) VALUES
                                                                       ('Max', 'Dog', 'Labrador', 5, 'D04X4H2'),
                                                                       ('Luna', 'Cat', 'Persian', 3, 'D04X4H2'),
                                                                       ('Charlie', 'Dog', 'Golden Retriever', 2, 'D06W3P1'),
                                                                       ('Bella', 'Dog', 'Poodle', 4, 'D02R6K8'),
                                                                       ('Lucy', 'Cat', 'Siamese', 6, 'D06W3P1'),
                                                                       ('Oscar', 'Dog', 'Labrador', 1, 'D02R6K8');