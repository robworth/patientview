package org.patientview.radar.model.enums;

public enum IssuePriority {
    LOW("Low"),
    NORMAL("Normal"),
    HIGH("High");

    private String name;

    private IssuePriority(String name) {
        this.name = name;
    }

    public static IssuePriority getIssuePriority(String name) {
        for (IssuePriority issuePriority: values()) {
            if (issuePriority.getName().equalsIgnoreCase(name)) {
                return issuePriority;
            }
        }

        return IssuePriority.LOW;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
