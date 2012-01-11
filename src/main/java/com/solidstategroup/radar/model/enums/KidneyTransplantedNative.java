package com.solidstategroup.radar.model.enums;

public enum KidneyTransplantedNative {

    // These are used as bits in the database but hopefully it'll convert to an int
    TRANSPLANTED(1), NATIVE(0);
    private int id;

    KidneyTransplantedNative(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
