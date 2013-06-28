package org.patientview.radar.model;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {

    private Long id;

    public boolean hasValidId() {
        return id != null && id > 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getLabelFromEnum(String enumString) {
        String label = enumString;
        label = label.replace("_", " ");
        String[] parts = label.split(" ");
        label = "";
        for (String part : parts) {
            String formatted = part.toLowerCase();
            formatted = Character.toUpperCase(formatted.charAt(0)) + formatted.substring(1);
            label += formatted;
            label += " ";
        }
        return label;
    }
}
