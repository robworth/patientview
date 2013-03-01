package com.worthsoln.patientview.model.enums;

/**
 *
 */
public enum DiagnosticType {
    IMAGING(1, "Imaging"),
    ENDOSCOPY(2, "Endoscopy");

    private int id;
    private String name;

    private DiagnosticType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static DiagnosticType getDiagnosticType(int id) {
        for (DiagnosticType type : DiagnosticType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }

        return null;
    }

    public static DiagnosticType getDiagnosticType(String s) {
        for (DiagnosticType type : DiagnosticType.values()) {
            if (type.getName().equalsIgnoreCase(s.trim())) {
                return type;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
