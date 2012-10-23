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
import com.worthsoln.service.SecurityUserManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.utils.LegacySpringUtils;
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
    private SecurityUserManager securityUserManager;

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
        return unitDao.get(unitCode, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public void save(Unit unit) {

        // set the tenancy against the unit if not already set
        if (unit.getTenancy() == null) {
            unit.setTenancy(securityUserManager.getLoggedInTenancy());
        }

        unitDao.save(unit);
    }

    @Override
    public List<Unit> getAll(boolean sortByName) {
        return unitDao.getAll(true, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<Unit> getUnitsWithUser() {
        return unitDao.getUnitsWithUser(securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<Unit> getLoggedInUsersUnits() {
        return getUsersUnits(userManager.getLoggedInUser());
    }

    @Override
    public List<Unit> getUsersUnits(User user) {
        List<String> usersUnitCodes = getUsersUnitCodes(user);
        return unitDao.get(usersUnitCodes, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<Unit> getLoggedInUsersUnits(String[] notTheseUnitCodes, String[] plusTheseUnitCodes) {

        List<String> usersUnitCodes = getUsersUnitCodes(userManager.getLoggedInUser());

        return unitDao.get(usersUnitCodes, notTheseUnitCodes, plusTheseUnitCodes,
                securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<String> getUsersUnitCodes(User user) {
        List<String> unitCodes = new ArrayList<String>();

        if (!LegacySpringUtils.getUserManager().getCurrentTenancyRole(user).equals("superadmin")) {

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
