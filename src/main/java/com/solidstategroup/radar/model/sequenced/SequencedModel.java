package com.solidstategroup.radar.model.sequenced;

import com.solidstategroup.radar.model.BaseModel;

public class SequencedModel extends BaseModel {

    private int sequenceNumber;

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
