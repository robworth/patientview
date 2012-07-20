package com.worthsoln.patientview;

import java.util.ArrayList;

import com.worthsoln.patientview.model.Patient;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.database.DatabaseUpdateQuery;
import com.worthsoln.database.StorableItem;

public class PatientDao extends StorableItem {

    protected Patient patient;

    public PatientDao(Patient patient) {
        this.patient = patient;
    }

    public PatientDao() {
        super();
    }

    public String[] getColumnNames() {
        return new String[]{
                "surname", "forename", "dateofbirth", "sex", "address1", "address2", "address3", "postcode",
                "telephone1",
                "telephone2", "mobile", "centreCode", "diagnosis", "treatment", "transplantstatus", "hospitalnumber",
                "gpname",
                "gpaddress1", "gpaddress2", "gpaddress3", "gppostcode", "gptelephone",
        };
    }

    public ArrayList getColumnParameters() {
        ArrayList params = new ArrayList();
        params.add(patient.getSurname());
        params.add(patient.getForename());
        params.add(patient.getDateofbirth());
        params.add(patient.getSex());
        params.add(patient.getAddress1());
        params.add(patient.getAddress2());
        params.add(patient.getAddress3());
        params.add(patient.getPostcode());
        params.add(patient.getTelephone1());
        params.add(patient.getTelephone2());
        params.add(patient.getMobile());
        params.add(patient.getCentreCode());
        params.add(patient.getDiagnosis());
        params.add(patient.getTreatment());
        params.add(patient.getTransplantstatus());
        params.add(patient.getHospitalnumber());
        params.add(patient.getGpname());
        params.add(patient.getGpaddress1());
        params.add(patient.getGpaddress2());
        params.add(patient.getGpaddress3());
        params.add(patient.getGppostcode());
        params.add(patient.getGptelephone());
        return params;
    }

    public Object getIdParameter() {
        return patient.getNhsno();
    }

    public Class getTableMapper() {
        return Patient.class;
    }

    public String getIdColumnName() {
        return "nhsno";
    }

    public String getTableName() {
        return "patient";
    }

    public DatabaseUpdateQuery getDeleteQuery() {
        Object[] parameters = new Object[]{patient.getNhsno(), patient.getCentreCode()};
        String sql = "DELETE FROM patient WHERE nhsno = ? AND centreCode = ?";
        return new DatabaseUpdateQuery(sql, parameters);
    }

    public DatabaseQuery getRetrieveQuery() {
        Object[] parameters = new Object[]{patient.getNhsno(), patient.getCentreCode()};
        String sql = "SELECT * FROM " + getTableName() + " WHERE nhsno = ? AND centreCode = ?";
        ResultSetHandler rsHandler = new BeanHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters, rsHandler);
    }
}
