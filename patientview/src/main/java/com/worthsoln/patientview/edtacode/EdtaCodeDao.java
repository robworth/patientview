package com.worthsoln.patientview.edtacode;

import java.util.ArrayList;
import com.worthsoln.database.StorableItem;
import com.worthsoln.patientview.model.EdtaCode;

public class EdtaCodeDao extends StorableItem {

    private EdtaCode edtaCode;

    public EdtaCodeDao(EdtaCode edtaCode) {
        this.edtaCode = edtaCode;
    }

    public EdtaCodeDao() {
        super();
    }

    public String[] getColumnNames() {
        return new String[] {
            "description", "medicalLink01", "medicalLink02", "medicalLink03", "medicalLink04", "medicalLink05",
            "medicalLink06", "medicalLinkText01", "medicalLinkText02", "medicalLinkText03", "medicalLinkText04",
            "medicalLinkText05", "medicalLinkText06", "patientLink01", "patientLink02", "patientLink03",
            "patientLink04", "patientLink05", "patientLink06", "patientLinkText01", "patientLinkText02",
            "patientLinkText03", "patientLinkText04", "patientLinkText05", "patientLinkText06",
        };
    }

    public ArrayList getColumnParameters() {
        ArrayList params = new ArrayList();

        params.add(edtaCode.getDescription());
        params.add(edtaCode.getMedicalLink01());
        params.add(edtaCode.getMedicalLink02());
        params.add(edtaCode.getMedicalLink03());
        params.add(edtaCode.getMedicalLink04());
        params.add(edtaCode.getMedicalLink05());
        params.add(edtaCode.getMedicalLink06());
        params.add(edtaCode.getMedicalLinkText01());
        params.add(edtaCode.getMedicalLinkText02());
        params.add(edtaCode.getMedicalLinkText03());
        params.add(edtaCode.getMedicalLinkText04());
        params.add(edtaCode.getMedicalLinkText05());
        params.add(edtaCode.getMedicalLinkText06());
        params.add(edtaCode.getPatientLink01());
        params.add(edtaCode.getPatientLink02());
        params.add(edtaCode.getPatientLink03());
        params.add(edtaCode.getPatientLink04());
        params.add(edtaCode.getPatientLink05());
        params.add(edtaCode.getPatientLink06());
        params.add(edtaCode.getPatientLinkText01());
        params.add(edtaCode.getPatientLinkText02());
        params.add(edtaCode.getPatientLinkText03());
        params.add(edtaCode.getPatientLinkText04());
        params.add(edtaCode.getPatientLinkText05());
        params.add(edtaCode.getPatientLinkText06());

        return params;
    }

    public Object getIdParameter() {
        return edtaCode.getEdtaCode();
    }

    public Class getTableMapper() {
        return EdtaCode.class;
    }

    public String getIdColumnName() {
        return "edtaCode";
    }

    public String getTableName() {
        return "edtaCode";
    }
}
