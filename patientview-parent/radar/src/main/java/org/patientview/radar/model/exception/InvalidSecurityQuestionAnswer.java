package org.patientview.radar.model.exception;

/**
 * thrown during professional registration is security question answer is incorrect
 */

public class InvalidSecurityQuestionAnswer extends Exception {
    public InvalidSecurityQuestionAnswer(String message) {
        super(message);
    }

    public InvalidSecurityQuestionAnswer(String message, Throwable cause) {
        super(message, cause);
    }
}
