package com.worthsoln.patientview.resultheading;

import java.util.ArrayList;
import java.util.Collection;

import com.worthsoln.patientview.model.ResultHeading;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.worthsoln.database.DatabaseQuery;
import com.worthsoln.database.StorableItem;
import com.worthsoln.patientview.Panel;

public class ResultHeadingDao extends StorableItem {

    private ResultHeading resultHeading;
    private Panel panel = new Panel(1);

    public ResultHeadingDao() {
    }

    public ResultHeadingDao(Panel panel) {
        if (panel != null) {
            this.panel = panel;
        }
    }

    public String[] getColumnNames() {
        return new String[]{"heading", "rollover", "link", "panel", "panelorder"};
    }

    public ArrayList getColumnParameters() {
        ArrayList params = new ArrayList();
        params.add(resultHeading.getHeading());
        params.add(resultHeading.getRollover());
        params.add(resultHeading.getLink());
        params.add(new Integer(resultHeading.getPanel()));
        params.add(new Integer(resultHeading.getPanelorder()));
        return params;
    }

    public Object getIdParameter() {
        return resultHeading.getHeadingcode();
    }

    public Collection getRetrieveListWhereClauseParameters() {
        ArrayList params = new ArrayList();
        params.add(new Integer(panel.getPanel()));
        return params;
    }

    public DatabaseQuery getRetrieveListQuery() {
        ArrayList parameters = new ArrayList();
        parameters.addAll(getRetrieveListWhereClauseParameters());
        String sql = "SELECT * FROM result_heading WHERE result_heading.panel = ? ORDER BY panelorder";
        ResultSetHandler rsHandler = new BeanListHandler(getTableMapper());
        return new DatabaseQuery(sql, parameters.toArray(), rsHandler);
    }

    public Class getTableMapper() {
        return ResultHeading.class;
    }

    public String getTableName() {
        return "result_heading";
    }
}
