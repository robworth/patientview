package com.solidstategroup.radar.model.enums;

public enum KidneyTransplantedNative {

    TRANSPLANTED(1), NATIVE(0);
    private int id;

    KidneyTransplantedNative(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
