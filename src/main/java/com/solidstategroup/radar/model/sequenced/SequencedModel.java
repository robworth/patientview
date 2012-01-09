package com.solidstategroup.radar.model.sequenced;

import com.solidstategroup.radar.model.BaseModel;

public class SequencedModel extends BaseModel {

    private Long radarNumber;
    private int sequenceNumber;

    public Long getRadarNumber() {
        return radarNumber;
    }

    public void setRadarNumber(Long radarNumber) {
        this.radarNumber = radarNumber;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
