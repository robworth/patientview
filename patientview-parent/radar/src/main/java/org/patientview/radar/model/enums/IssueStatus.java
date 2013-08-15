package org.patientview.radar.model.enums;

public enum IssueStatus {
    OPEN("Open"),
    PENDING("Pending"),
    CLOSED("Closed"),
    POSTPONED("Postponed");

    private String name;

    private IssueStatus(String name) {
        this.name = name;
    }

    public static IssueStatus getIssueStatus(String name) {
        for (IssueStatus issueStatus: values()) {
            if (issueStatus.getName().equalsIgnoreCase(name)) {
                return issueStatus;
            }
        }

        return IssueStatus.OPEN;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }    
}
