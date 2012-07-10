package com.worthsoln.patientview.uktransplant;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.database.RetrievableItem;

public class UktStatusForPatientDao implements RetrievableItem {

    private String nhsno;

    public UktStatusForPatientDao(String nhsno) {
        this.nhsno = nhsno;
    }

    public DatabaseQuery getRetrieveQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT uktstatus.nhsno, uktkidneycode.description AS uktkidney, "
                + "uktpancreascode.description AS uktpancreas "
                + "FROM uktstatus, uktcode AS uktkidneycode, uktcode AS uktpancreascode "
                + "WHERE uktstatus.nhsno = ? "
                + "AND uktstatus.kidney = uktkidneycode.uktcode "
                + "AND uktstatus.pancreas = uktpancreascode.uktcode";
        ResultSetHandler rsHandler = new BeanListHandler(UktStatusForPatient.class);
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();
        params.add(nhsno);
        return params;
    }
}
