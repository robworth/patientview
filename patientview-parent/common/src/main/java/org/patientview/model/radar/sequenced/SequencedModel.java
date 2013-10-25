package org.patientview.model.radar.sequenced;

import org.patientview.model.radar.RadarModel;

public class SequencedModel extends RadarModel {

    private Integer sequenceNumber;

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
