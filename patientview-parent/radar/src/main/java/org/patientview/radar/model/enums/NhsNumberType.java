package org.patientview.radar.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public enum NhsNumberType {

    // Note: the name needs to be in the format for the wicket pages to work.
    NHS_NUMBER(1, "NHS"),
    CHI_NUMBER(2, "CHI");

    private long id;
    private String name;

    NhsNumberType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<String> getNhsNumberTypeNames() {
        List<String> names = new ArrayList<String>();
        for (NhsNumberType nhsNumberType : NhsNumberType.values()) {
            names.add(nhsNumberType.getName());
        }

        return names;
    }

    public static List<NhsNumberType> getNhsNumberTypesAsList() {
        return Arrays.asList(NhsNumberType.values());
    }

    public static NhsNumberType getNhsNumberType(long id) {
        for (NhsNumberType nhsNumberType : NhsNumberType.values()) {
            if (nhsNumberType.getId() == id) {
                return nhsNumberType;
            }
        }

        return null;
    }

    public static NhsNumberType getNhsNumberType(String name) {
        if (name != null && name.length() > 0) {
            for (NhsNumberType nhsNumberType : NhsNumberType.values()) {
                if (nhsNumberType.getName().equals(name)) {
                    return nhsNumberType;
                }
            }
        }

        return null;
    }
}
