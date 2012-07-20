package com.worthsoln.patientview.letter;

import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.database.StorableItem;
import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.User;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.ArrayList;
import java.util.Collection;


public class LetterDao extends StorableItem {

    private User user;


    public LetterDao(User user) {
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
        String sql = "SELECT letter.* FROM letter, usermapping "
                + "WHERE usermapping.username = ? "
                + "AND usermapping.nhsno = letter.nhsno "
                + "AND usermapping.unitcode = letter.unitcode "
                + "ORDER BY letter.date ";
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
        return Letter.class;
    }

    public String getTableName() {
        return "letter";
    }
}
