/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

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

