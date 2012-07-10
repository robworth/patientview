package com.worthsoln.patientview.uktransplant;

import java.util.ArrayList;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.patientview.PatientDao;

public class UktPatientDao extends PatientDao {

    public String[] getColumnNames() {
        return new String[]{"surname", "forename", "dateofbirth", "postcode",};
    }

    public ArrayList getColumnParameters() {
        ArrayList params = new ArrayList();
        params.add(patient.getSurname());
        params.add(patient.getForename());
        params.add(patient.getDateofbirth());
        params.add(patient.getPostcode());
        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT DISTINCT patient.nhsno, patient.surname, patient.forename, " +
                " patient.dateofbirth, patient.postcode FROM patient, user, usermapping " +
                " WHERE patient.nhsno REGEXP '^[0-9]{10}$' AND patient.nhsno = usermapping.nhsno AND user.username = usermapping.username " +
                " AND usermapping.username NOT LIKE '%-GP' AND user.dummypatient = 0";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }
}
