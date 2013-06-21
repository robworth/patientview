package com.solidstategroup.radar.model.enums;

import com.solidstategroup.radar.model.BaseModel;

public enum RemissionAchieved {

    COMPLETE(1), PARTIAL(2), NONE(3);
    private int id;

    RemissionAchieved(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return BaseModel.getLabelFromEnum(toString());
    }
}
