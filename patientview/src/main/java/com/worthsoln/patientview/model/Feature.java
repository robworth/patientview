package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pv_feature_access")
public class Feature extends BaseModel {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    public Feature() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
