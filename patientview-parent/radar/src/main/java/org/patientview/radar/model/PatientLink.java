package org.patientview.radar.model;

/**
 * This class links two patient records together. One that is editable and only displays in Radar, the other
 * belows to an external unit and gets import via the XML Importer
 *
 * User: james@solidstategroup.com
 * Date: 14/11/13
 * Time: 16:01
 */
public class PatientLink extends BaseModel {

    public String sourceNhsNO;
    public String sourceUnit;
    public String destinationNhsNo;
    public String destinationUnit;

    public String getSourceNhsNO() {
        return sourceNhsNO;
    }

    public void setSourceNhsNO(String sourceNhsNO) {
        this.sourceNhsNO = sourceNhsNO;
    }

    public String getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(String sourceUnit) {
        this.sourceUnit = sourceUnit;
    }

    public String getDestinationNhsNo() {
        return destinationNhsNo;
    }

    public void setDestinationNhsNo(String destinationNhsNo) {
        this.destinationNhsNo = destinationNhsNo;
    }

    public String getDestinationUnit() {
        return destinationUnit;
    }

    public void setDestinationUnit(String destinationUnit) {
        this.destinationUnit = destinationUnit;
    }
}
