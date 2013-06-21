package org.patientview.radar.model.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Thrown by the service layer when the model to save is invalid
 */

public class InvalidModelException extends Exception {

    private List<String> errors = new ArrayList<String>();

    public InvalidModelException(String message) {
        super(message);
    }

    public InvalidModelException(String message, Throwable cause) {
        super(message, cause);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
