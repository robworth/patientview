package org.patientview.radar.model;

public class Modality extends BaseModel {

    private String type;
    private int group;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
