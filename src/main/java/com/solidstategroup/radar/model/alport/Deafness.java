package com.solidstategroup.radar.model.alport;

import com.solidstategroup.radar.model.BaseModel;

import java.util.Arrays;
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
    private int ageProblemFirstNoticed;
    private int ageStartedUsingHearingAid;

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

    public int getAgeProblemFirstNoticed() {
        return ageProblemFirstNoticed;
    }

    public void setAgeProblemFirstNoticed(int ageProblemFirstNoticed) {
        this.ageProblemFirstNoticed = ageProblemFirstNoticed;
    }

    public int getAgeStartedUsingHearingAid() {
        return ageStartedUsingHearingAid;
    }

    public void setAgeStartedUsingHearingAid(int ageStartedUsingHearingAid) {
        this.ageStartedUsingHearingAid = ageStartedUsingHearingAid;
    }
}
