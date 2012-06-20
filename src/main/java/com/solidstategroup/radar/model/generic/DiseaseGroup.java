package com.solidstategroup.radar.model.generic;


import com.solidstategroup.radar.model.BaseModel;

import java.io.Serializable;

/**
 * rdr_disease_group
 */
public class DiseaseGroup extends BaseModel implements Serializable{
    public static final Long SRNS_DISEASE_GROUP_ID = 42L; // todo change this for live and check ids for dev
    public static final Long MPGN_DISEASEGROUP_ID = 32L; // todo copy this for live and check ids for dev
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
