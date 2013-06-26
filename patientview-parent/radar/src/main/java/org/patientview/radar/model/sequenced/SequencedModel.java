package org.patientview.radar.model.sequenced;

import org.patientview.radar.model.RadarModel;

public class SequencedModel extends RadarModel {

    private Integer sequenceNumber;

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
