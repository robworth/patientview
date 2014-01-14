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

import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.UnitDao;
import org.patientview.radar.dao.UserDao;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.model.user.DemographicsUserDetail;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.PatientManager;

import java.util.List;

public class DemographicsManagerImpl implements DemographicsManager {


    private DemographicsDao demographicsDao;

    private UserDao userDao;

    private UnitDao unitDao;

    private PatientManager patientManager;

    public Patient get(Long id) {
        return demographicsDao.get(id);
    }


    public List<Patient> getDemographics() {
        return getDemographics(new DemographicsFilter(), -1, -1);
    }

    public List<Patient> getDemographics(DemographicsFilter filter) {
        return getDemographics(filter, -1, -1);
    }

    public List<Patient> getDemographics(DemographicsFilter filter, int page, int numberPerPage) {
        return demographicsDao.getDemographics(filter, page, numberPerPage);
    }

    public Sex getSex(long id) {
        return demographicsDao.getSex(id);
    }

    public List<Sex> getSexes() {
        return demographicsDao.getSexes();
    }

    public Status getStatus(long id) {
        return demographicsDao.getStatus(id);
    }

    // Needs refactoring out. Patient Data Provider needs creating
    public List<Patient> getDemographicsByUser(User user) {

        List<String> unitCodes;
        if (user.getSecurityRole().equals(User.ROLE_SUPER_USER)) {
            unitCodes = unitDao.getAllUnitCodes();
        } else {
            unitCodes = unitDao.getUnitCodes(user);
        }

        return patientManager.getPatientsByUnitCode(unitCodes);
    }

    public List<Status> getStatuses() {
        return demographicsDao.getStatuses();
    }

    public DemographicsDao getDemographicsDao() {
        return demographicsDao;
    }

    public void setDemographicsDao(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
    }

    public DemographicsUserDetail getDemographicsUserDetail(String nhsno, String unitcode) {
        return demographicsDao.getDemographicsUserDetail(nhsno, unitcode);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUnitDao(UnitDao unitDao) {
        this.unitDao = unitDao;
    }

    public void setPatientManager(PatientManager patientManager) {
        this.patientManager = patientManager;
    }
}


