package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 *  Common attributes across all model objects
 */
@MappedSuperclass
public class BaseModel implements Serializable, Comparable {

    protected BaseModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)   // let hibernate pick the strategy based on underlying db
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(this.getClass().isAssignableFrom(o.getClass()))) {
            return false;
        }

        BaseModel baseModel = (BaseModel) o;

        return id != null && id.equals(baseModel.id);
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return -1;
        }
    }

    @Override
    public int compareTo(Object o) {

        BaseModel baseModel = (BaseModel) o;

        return this.id.compareTo(baseModel.getId());
    }
}