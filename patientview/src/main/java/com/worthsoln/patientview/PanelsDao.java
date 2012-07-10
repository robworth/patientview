package com.worthsoln.patientview;

import java.util.ArrayList;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.database.RetrievableList;

public class PanelsDao implements RetrievableList {

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();

        String sql = "SELECT DISTINCT panel FROM result_heading WHERE panel != 0 ORDER BY panel ASC";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());

        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return Panel.class;
    }
}
