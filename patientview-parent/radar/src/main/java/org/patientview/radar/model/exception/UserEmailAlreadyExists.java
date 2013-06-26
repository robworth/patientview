package org.patientview.radar.model.exception;

/**
 * thrown during professional registration is email address already exists
 */

public class UserEmailAlreadyExists extends Exception {
    public UserEmailAlreadyExists(String message) {
        super(message);
    }

    public UserEmailAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}
