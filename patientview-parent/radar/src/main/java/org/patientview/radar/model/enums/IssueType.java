package org.patientview.radar.model.enums;

public enum IssueType {
    ADDITION("Addition"),
    CORRECTION("Correction"),
    REVISION("Revision");

    private String name;

    private IssueType(String name) {
        this.name = name;
    }

    public static IssueType getIssueType(String name) {
        for (IssueType issueType: values()) {
            if (issueType.getName().equalsIgnoreCase(name)) {
                return issueType;
            }
        }

        return IssueType.ADDITION;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }    
}
