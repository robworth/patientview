package com.worthsoln.patientview.medicine;

import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.database.StorableItem;
import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.patientview.model.User;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.Collection;


public class MedicineDao extends StorableItem {

    private User user;


    public MedicineDao(User user) {
        this.user = user;
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();
        params.add(user.getUsername());
        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT DISTINCT medicine.* FROM medicine " +
                " LEFT JOIN usermapping ON medicine.nhsno = usermapping.nhsno " +
                " WHERE usermapping.username = ? " +
                " ORDER BY medicine.unitcode, medicine.startdate ";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }


    public String[] getColumnNames() {
        return new String[0];
    }

    public ArrayList getColumnParameters() {
        return null;
    }

    public Object getIdParameter() {
        return null;
    }

    public Class getTableMapper() {
        return Medicine.class;
    }

    public String getTableName() {
        return "medicine";
    }
}