package org.patientview.patientview.model;

import org.patientview.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.util.Arrays;
import java.util.List;

@Entity(name = "rdc_genetic_test")
public class Genetics extends BaseModel {

    public enum TestsDone {
        INVAILD(0, "Invaild data"),
        NO(1, "No"),
        YES_IN_THIS_PATIENT(2, "Yes, in this patient"),
        YES_IN_ANOTHER_FAMILY_MEMBER(3, "Yes, in another family member");

        private int id;
        private String value;

        private TestsDone(int id, String value) {
            this.id = id;
            this.value = value;
        }

        public static TestsDone getTestsDone(int id) {
            for (TestsDone testsDone : TestsDone.values()) {
                if (testsDone.getId() == id) {
                    return testsDone;
                }
            }

            return TestsDone.NO;
        }

        public List<TestsDone> getAsList() {
            return Arrays.asList(TestsDone.values());
        }

        public int getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }

    @Column(nullable = false, name = "radar_no")
    private Long radarNo;

    @Enumerated
    @Column(nullable = false)
    private TestsDone testsDone;

    @Column(nullable = true)
    private String labWhereTestWasDone;

    @Column(nullable = true)
    private String testDoneOn;

    @Column(nullable = true)
    private String referenceNumber;

    @Column(nullable = true)
    private String whatResultsShowed;

    @Column(nullable = true)
    private String keyEvidence;

    public Long getRadarNo() {
        return radarNo;
    }

    public void setRadarNo(Long radarNo) {
        this.radarNo = radarNo;
    }

    public TestsDone getTestsDone() {
        return testsDone;
    }

    public void setTestsDone(TestsDone testsDone) {
        this.testsDone = testsDone;
    }

    public String getLabWhereTestWasDone() {
        return labWhereTestWasDone;
    }

    public void setLabWhereTestWasDone(String labWhereTestWasDone) {
        this.labWhereTestWasDone = labWhereTestWasDone;
    }

    public String getTestDoneOn() {
        return testDoneOn;
    }

    public void setTestDoneOn(String testDoneOn) {
        this.testDoneOn = testDoneOn;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getWhatResultsShowed() {
        return whatResultsShowed;
    }

    public void setWhatResultsShowed(String whatResultsShowed) {
        this.whatResultsShowed = whatResultsShowed;
    }

    public String getKeyEvidence() {
        return keyEvidence;
    }

    public void setKeyEvidence(String keyEvidence) {
        this.keyEvidence = keyEvidence;
    }
}
