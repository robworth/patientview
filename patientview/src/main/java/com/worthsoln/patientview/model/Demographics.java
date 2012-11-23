package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "tbl_demographics")
public class Demographics extends BaseModel {

    @Column(name = "radar_no")
    private long radarNo;

    public Demographics() {
    }

    public long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(long radarNo) {
        this.radarNo = radarNo;
    }

}
