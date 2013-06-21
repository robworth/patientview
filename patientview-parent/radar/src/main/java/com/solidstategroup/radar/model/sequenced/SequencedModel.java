package com.solidstategroup.radar.model.sequenced;

import com.solidstategroup.radar.model.RadarModel;

public class SequencedModel extends RadarModel {

    private Integer sequenceNumber;

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
