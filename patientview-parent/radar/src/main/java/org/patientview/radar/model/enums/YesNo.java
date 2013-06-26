package org.patientview.radar.model.enums;


import java.util.Arrays;
import java.util.List;

public enum YesNo {

    YES(1, "Yes"), NO(0, "No");

    private int id;
    private String value;

    private YesNo(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static YesNo getYesNo(int id) {
        for (YesNo yesNo : YesNo.values()) {
            if (yesNo.getId() == id) {
                return yesNo;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public List<YesNo> getAsList() {
        return Arrays.asList(YesNo.values());
    }
}
