package com.solidstategroup.radar.model.generic;


import com.solidstategroup.radar.model.BaseModel;

import java.io.Serializable;

/**
 * rdr_disease_group
 */
public class DiseaseGroup extends BaseModel implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
