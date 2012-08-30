package com.solidstategroup.radar.model.generic;

import java.io.Serializable;

/**
 * using 'unit' table in db
 * also sometimes referred to as working group
 */
public class DiseaseGroup implements Serializable, Comparable<DiseaseGroup> {
    public static final String SRNS_DISEASE_GROUP_ID = "SRNS";
    public static final String MPGN_DISEASEGROUP_ID = "MPGN";
    public static final String ALPORT_DISEASEGROUP_ID = "ALPORT";
    private String id;
    private String name;
    private String shortName;

    public int compareTo(DiseaseGroup o) {
        return name.compareTo(o.getName()); // todo might need to compare short names
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
