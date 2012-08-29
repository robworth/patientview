package com.worthsoln.ibd.model.enums;

public enum Severity {
    SEVERE(1, 16),
    MODERATE(2, 8),
    MILD(3, 4);

    private long id;
    private int defaultLevel;

    private Severity(long id, int defaultLevel) {
        this.id = id;
        this.defaultLevel = defaultLevel;
    }

    public static Severity getSeverity(Long id) {
        for (Severity severity : Severity.values()) {
            if (severity.getId() == id) {
                return severity;
            }
        }

        return null;
    }

    public long getId() {
        return id;
    }

    public int getDefaultLevel() {
        return defaultLevel;
    }
}
