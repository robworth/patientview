package com.solidstategroup.radar.model.sequenced;

import com.solidstategroup.radar.model.RadarModel;

public class SequencedModel extends RadarModel {

    private int sequenceNumber;

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
