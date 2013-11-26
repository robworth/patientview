package org.patientview.radar.model.enums;

/**
 * User: james@solidstategroup.com
 * Date: 26/11/13
 * Time: 12:04
 */
public enum SourceType {

    RADAR("Radar"),
    PATIENT_VIEW("PatientView"),
    XML_IMPORTER("XMLImporter") ;

    private String name;

    private SourceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
