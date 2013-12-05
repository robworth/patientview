package org.patientview.radar.exception;

/**
 * User: james@solidstategroup.com
 * Date: 20/11/13
 * Time: 13:51
 */
public class UserRoleException extends Exception {

    public UserRoleException(String message) {
        super(message);
    }

    public UserRoleException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
