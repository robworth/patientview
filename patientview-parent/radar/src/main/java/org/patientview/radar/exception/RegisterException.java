package org.patientview.radar.exception;

/**
 * User: james@solidstategroup.com
 * Date: 20/11/13
 * Time: 14:05
 */
public class RegisterException extends Exception{

    public RegisterException(String message) {
        super(message);
    }

    public RegisterException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
