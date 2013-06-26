package org.patientview.radar.model.enums;

import org.patientview.radar.model.BaseModel;

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
