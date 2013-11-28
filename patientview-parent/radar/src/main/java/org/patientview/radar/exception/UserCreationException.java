package org.patientview.radar.exception;

/**
 * User: james@solidstategroup.com
 * Date: 20/11/13
 * Time: 13:32
 */
public class UserCreationException extends Exception {

    public UserCreationException(String message) {
        super(message);
    }

    public UserCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
