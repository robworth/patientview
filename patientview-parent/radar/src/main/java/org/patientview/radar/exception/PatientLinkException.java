package org.patientview.radar.exception;

/**
 * User: james@solidstategroup.com
 * Date: 20/11/13
 * Time: 13:32
 */
public class PatientLinkException extends Exception {

    public PatientLinkException(String message) {
        super(message);
    }

    public PatientLinkException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
