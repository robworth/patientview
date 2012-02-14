package com.solidstategroup.radar.model.exception;

/**
 * thrown during professional registration is email address already exists
 */

public class ProfessionalUserEmailAlreadyExists extends Exception {
    public ProfessionalUserEmailAlreadyExists(String message) {
        super(message);
    }

    public ProfessionalUserEmailAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
