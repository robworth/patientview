package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UktStatus extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column
    private String kidney;

    @Column
    private String pancreas;

    public UktStatus() {
    }

    public UktStatus(String nhsno) {
        this.nhsno = nhsno;
    }

    public UktStatus(String nhsno, String kidney, String pancreas) {
        this.nhsno = nhsno;
        this.kidney = kidney;
        this.pancreas = pancreas;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getKidney() {
        return kidney;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public String getPancreas() {
        return pancreas;
    }

    public void setPancreas(String pancreas) {
        this.pancreas = pancreas;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UktStatus)) {
            return false;
        }
        final UktStatus uktStatus = (UktStatus) o;
        if (kidney != null ? !kidney.equals(uktStatus.kidney) : uktStatus.kidney != null) {
            return false;
        }
        if (nhsno != null ? !nhsno.equals(uktStatus.nhsno) : uktStatus.nhsno != null) {
            return false;
        }
        if (pancreas != null ? !pancreas.equals(uktStatus.pancreas) : uktStatus.pancreas != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        result = (nhsno != null ? nhsno.hashCode() : 0);
        result = 29 * result + (kidney != null ? kidney.hashCode() : 0);
        result = 29 * result + (pancreas != null ? pancreas.hashCode() : 0);
        return result;
    }
}
