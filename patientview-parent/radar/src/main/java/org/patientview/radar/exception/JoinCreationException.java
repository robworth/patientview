package org.patientview.radar.exception;

/**
 * User: james@solidstategroup.com
 * Date: 20/11/13
 * Time: 13:56
 */
public class JoinCreationException extends Exception {

    public JoinCreationException(String message) {
        super(message);
    }

    public JoinCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
