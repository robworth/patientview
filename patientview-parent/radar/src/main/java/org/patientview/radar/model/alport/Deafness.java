package org.patientview.radar.model.alport;

import org.patientview.radar.model.BaseModel;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Deafness extends BaseModel {

    public enum EvidenceOfDeafness {
        NO(1, "No"),
        YES_MINOR(2, "Yes, Minor"),
        YES_HEARING_AID_NEEDED(3, "Yes, Hearing aid needed");

        private int id;
        private String value;

        private EvidenceOfDeafness(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public static EvidenceOfDeafness getEvidenceOfDeafness(int id) {
            for (EvidenceOfDeafness evidenceOfDeafness : EvidenceOfDeafness.values()) {
                if (evidenceOfDeafness.getId() == id) {
                    return evidenceOfDeafness;
                }
            }

            return EvidenceOfDeafness.NO;
        }

        public List<EvidenceOfDeafness> getAsList() {
            return Arrays.asList(EvidenceOfDeafness.values());
        }

        public int getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }

    private Long radarNo;
    private EvidenceOfDeafness evidenceOfDeafness;
    private Date dateProblemFirstNoticed;
    private Date dateStartedUsingHearingAid;

    public Long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(Long radarNo) {
        this.radarNo = radarNo;
    }

    public EvidenceOfDeafness getEvidenceOfDeafness() {
        return evidenceOfDeafness;
    }

    public void setEvidenceOfDeafness(EvidenceOfDeafness evidenceOfDeafness) {
        this.evidenceOfDeafness = evidenceOfDeafness;
    }

    public Date getDateProblemFirstNoticed() {
        return dateProblemFirstNoticed;
    }

    public void setDateProblemFirstNoticed(Date dateProblemFirstNoticed) {
        this.dateProblemFirstNoticed = dateProblemFirstNoticed;
    }

    public Date getDateStartedUsingHearingAid() {
        return dateStartedUsingHearingAid;
    }

    public void setDateStartedUsingHearingAid(Date dateStartedUsingHearingAid) {
        this.dateStartedUsingHearingAid = dateStartedUsingHearingAid;
    }
}
