package org.patientview.radar.model.exception;


public class EmailAddressNotFoundException extends Exception {
    public EmailAddressNotFoundException(String message) {
        super(message);
    }

    public EmailAddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
