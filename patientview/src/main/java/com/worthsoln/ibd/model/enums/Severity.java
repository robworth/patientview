package com.worthsoln.ibd.model.enums;

public enum Severity {
    SEVERE(1, 16, 10),
    MODERATE(2, 8, 6),
    MILD(3, 4, 4);

    private long id;
    private int crohnsDefaultLevel;
    private int colitisDefaultLevel;

    private Severity(long id, int crohnsDefaultLevel, int colitisDefaultLevel) {
        this.id = id;
        this.crohnsDefaultLevel = crohnsDefaultLevel;
        this.colitisDefaultLevel = colitisDefaultLevel;
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

    public int getDefaultLevel(Diagnosis diagnosis) {
        if (diagnosis.equals(Diagnosis.CROHNS)) {
            return crohnsDefaultLevel;
        } else {
            return colitisDefaultLevel;
        }
    }

    public int getCrohnsDefaultLevel() {
        return crohnsDefaultLevel;
    }

    public int getColitisDefaultLevel() {
        return colitisDefaultLevel;
    }
}
