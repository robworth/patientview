package com.worthsoln.service.impl;

import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.PatientCount;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.model.UnitStat;
import com.worthsoln.repository.PatientCountDao;
import com.worthsoln.repository.UnitDao;
import com.worthsoln.repository.UnitStatDao;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service(value = "unitManager")
public class UnitManagerImpl implements UnitManager {

    @Inject
    private PatientCountDao patientCountDao;

    @Inject
    private UnitDao unitDao;

    @Inject
    private UnitStatDao unitStatDao;

    @Inject
    private UserManager userManager;

    @Override
    public Unit get(Long id) {
        return unitDao.get(id);
    }

    @Override
    public Unit get(String unitCode) {
        return unitDao.get(unitCode);
    }

    @Override
    public void save(Unit unit) {
        unitDao.save(unit);
    }

    @Override
    public List<Unit> getAll(boolean sortByName) {
        return unitDao.getAll(true);
    }

    @Override
    public List<Unit> getUnitsWithUser() {
        return unitDao.getUnitsWithUser();
    }

    @Override
    public List<Unit> getLoggedInUsersUnits() {

        List<String> usersUnitCodes = getUsersUnitCodes();

        return unitDao.get(usersUnitCodes);
    }

    @Override
    public List<Unit> getLoggedInUsersUnits(String[] notTheseUnitCodes, String[] plusTheseUnitCodes) {

        List<String> usersUnitCodes = getUsersUnitCodes();

        return unitDao.get(usersUnitCodes, notTheseUnitCodes, plusTheseUnitCodes);
    }

    @Override
    public List<String> getUsersUnitCodes() {

        User user = userManager.getLoggedInUser();

        List<String> unitCodes = new ArrayList<String>();

        if (!user.getRole().equals("superadmin")) {

            List<UserMapping> userMappings = userManager.getUserMappings(user.getUsername());

            for (UserMapping userMapping : userMappings) {
                if (!UnitUtils.PATIENT_ENTERS_UNITCODE.equalsIgnoreCase(userMapping.getUnitcode())) {
                    unitCodes.add(userMapping.getUnitcode());
                }
            }
        }

        return unitCodes;
    }

    @Override
    public List<UnitStat> getPatientCountsForUnit(String unitCode) {

        List<PatientCount> patientCounts = patientCountDao.get(unitCode, "patient");

        List<UnitStat> patientCountStats = new ArrayList<UnitStat>();
        for (PatientCount patientCount : patientCounts) {
            String yearmonth = patientCount.getYearmonth();
            int count = patientCount.getCount();
            patientCountStats.add(new UnitStat(unitCode, yearmonth, AddLog.PATIENT_COUNT, count));
        }

        return patientCountStats;
    }

    @Override
    public List<UnitStat> getUnitStatsForUnit(String unitCode) {
        return unitStatDao.get(unitCode);
    }
}
