package org.patientview.radar.service.impl;

import org.patientview.model.Unit;
import org.patientview.model.enums.UnitSourceType;
import org.patientview.radar.dao.UnitDao;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.UnitManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This is in Radar but all here too.
 *
 * User: james@solidstategroup.com
 * Date: 04/12/13
 * Time: 17:39
 */
public class UnitManagerImpl implements UnitManager {

    private UnitDao unitDao;

    public List<String> getUnitCodes(User user) {
        return unitDao.getUnitCodes(user);
    }

    public List<Unit> getRenalUnits(User user) {

        return getUnits(user, UnitSourceType.RENAL_UNIT);

    }

    public List<Unit> getDiseaseUnits(User user) {

        return getUnits(user, UnitSourceType.RADAR_GROUP);

    }


    private List<Unit> getUnits(User user, UnitSourceType unitSourceType) {
        List<Unit> units = new ArrayList<Unit>();

        for (Unit unit : unitDao.getUnits(user)) {
            if (unit.getSourceType().equals(unitSourceType.getName())) {
                units.add(unit);
            }
        }

        return units;

    }

    public List<Unit> getUnits(User user) {
        return unitDao.getUnits(user);
    }

    public void setUnitDao(UnitDao unitDao) {
        this.unitDao = unitDao;
    }
}

