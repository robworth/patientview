package org.patientview.radar.model.exception;

/**
 * Thrown in the case of problems registering a patient
 */
public class RegistrationException extends Exception {

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(String message, Exception e) {
        super(message, e);
    }

}
