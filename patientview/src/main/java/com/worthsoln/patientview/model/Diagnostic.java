package com.worthsoln.patientview.model;

import com.worthsoln.patientview.model.enums.DiagnosticType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *      Generic holder for exams and procedure dates
 */
@Entity
public class Diagnostic extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false)
    private Calendar datestamp;

    @Transient
    private DiagnosticType diagnosticType;

    @Column(nullable = false)
    private String description;

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public Calendar getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Calendar datestamp) {
        this.datestamp = datestamp;
    }

    public DiagnosticType getDiagnosticType() {
        return diagnosticType;
    }

    public void setDiagnosticType(DiagnosticType diagnosticType) {
        this.diagnosticType = diagnosticType;
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "diagnostic_type_id", nullable = false)
    public int getDiagnosticTypeId() {
        if (diagnosticType != null) {
            return diagnosticType.getId();
        }

        return -1;
    }

    public void setDiagnosticTypeId(int diagnosticTypeId) {
        this.diagnosticType = DiagnosticType.getDiagnosticType(diagnosticTypeId);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public String getFormattedDatestamp() {
        if (datestamp != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
            if ((datestamp.get(Calendar.HOUR_OF_DAY) == 0) && (datestamp.get(Calendar.MINUTE) == 0)) {
                return dateFormat.format(datestamp.getTime());
            } else {
                return dateTimeFormat.format(datestamp.getTime());
            }
        }

        return "";
    }
}
