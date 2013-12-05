package org.patientview.model.enums;

/**
 * User: james@solidstategroup.com
 * Date: 04/12/13
 * Time: 17:51
 */
public enum UnitSourceType {

    RENAL_UNIT("renalunit"),
    RADAR_GROUP("radargroup");

    private String name;

    private UnitSourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
