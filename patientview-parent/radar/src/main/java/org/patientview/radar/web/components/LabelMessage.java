package org.patientview.radar.web.components;

import java.io.Serializable;

/**
 * User: james@solidstategroup.com
 * Date: 22/11/13
 * Time: 17:04
 */
public class LabelMessage implements Serializable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
