package org.patientview.radar.model;

public class Immunosuppression extends BaseModel {

    private String description;
    private int group;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
