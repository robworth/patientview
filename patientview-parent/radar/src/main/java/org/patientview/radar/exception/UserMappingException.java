package org.patientview.radar.exception;

/**
 * User: james@solidstategroup.com
 * Date: 20/11/13
 * Time: 13:32
 */
public class UserMappingException extends Exception {

    public UserMappingException(String message) {
        super(message);
    }

    public UserMappingException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
