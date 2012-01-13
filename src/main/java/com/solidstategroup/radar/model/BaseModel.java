package com.solidstategroup.radar.model;

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
}
