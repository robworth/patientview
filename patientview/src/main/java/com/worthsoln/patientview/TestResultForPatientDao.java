package com.worthsoln.patientview;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;

public class TestResultForPatientDao extends TestResultDao {

    private String username;
    private int panel = 1;

    public TestResultForPatientDao(String username) {
        this.username = username;
    }

    public TestResultForPatientDao(String username, Panel panel) {
        this.username = username;
        if (panel != null) {
            this.panel = panel.getPanel();
        }
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();
        params.add(username);
        params.add(new Integer(panel));
        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT testresult.*, unit.shortname FROM testresult, user, usermapping, result_heading, unit " +
                "WHERE user.username = ? " +
                "AND user.username = usermapping.username " +
                "AND usermapping.nhsno = testresult.nhsno " +
                "AND testresult.testcode = result_heading.headingcode " +
                "AND testresult.unitcode = unit.unitcode " +
                "AND result_heading.panel = ?";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return TestResultWithUnitShortname.class;
    }
}
