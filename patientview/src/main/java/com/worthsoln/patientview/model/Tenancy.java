package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 */
@Entity
public class Tenancy extends BaseModel {

    @Column(nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
