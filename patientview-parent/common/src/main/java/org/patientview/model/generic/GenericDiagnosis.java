package org.patientview.model.generic;

import java.io.Serializable;

/**
 * table name: rdr_prd_code
 */
public class GenericDiagnosis implements Comparable<GenericDiagnosis>, Serializable {
    private String id;
    private String term;
    private Integer order;
    private String prdCode;
    private String workingGroup;

    public int compareTo(GenericDiagnosis diagnosis) {
        return order - diagnosis.getOrder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public String getWorkingGroup() {
        return workingGroup;
    }

    public void setWorkingGroup(String workingGroup) {
        this.workingGroup = workingGroup;
    }
}
