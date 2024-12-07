// InvalidDataException.java
package com.example.ClaudeSpringApplication.services.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}