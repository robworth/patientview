package com.worthsoln.patientview;

import java.util.ArrayList;
import com.worthsoln.database.StorableItem;

public class CentreDao extends StorableItem {

    private Centre centre;

    public CentreDao(Centre centre) {
        this.centre = centre;
    }

    public CentreDao() {
        super();
    }

    public String[] getColumnNames() {
        return new String[] {
            "centreName", "centreAddress1", "centreAddress2", "centreAddress3", "centreAddress4", "centrePostCode",
            "centreTelephone", "centreEmail",
        };
    }

    public ArrayList getColumnParameters() {
        ArrayList params = new ArrayList();

        params.add(centre.getCentreName());
        params.add(centre.getCentreAddress1());
        params.add(centre.getCentreAddress2());
        params.add(centre.getCentreAddress3());
        params.add(centre.getCentreAddress4());
        params.add(centre.getCentrePostCode());
        params.add(centre.getCentreTelephone());
        params.add(centre.getCentreEmail());

        return params;
    }

    public Object getIdParameter() {
        return centre.getCentreCode();
    }

    public Class getTableMapper() {
        return Centre.class;
    }

    public String getIdColumnName() {
        return "centreCode";
    }

    public String getTableName() {
        return "centre";
    }
}
