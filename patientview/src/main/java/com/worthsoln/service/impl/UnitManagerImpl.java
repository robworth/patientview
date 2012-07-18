package com.worthsoln.service.impl;

import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.repository.UnitDao;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UnitManagerImpl implements UnitManager {

    @Inject
    private UnitDao unitDao;

    @Inject
    private UserManager userManager;

    @Override
    public Unit get(Long id) {
        return unitDao.get(id);
    }

    @Override
    public List<Unit> getAll(boolean sortByName) {
        return unitDao.getAll(true);
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

            List<UserMapping> userMappings = userManager.getUserMappings(user);

            for (UserMapping userMapping : userMappings) {
                if (!UnitUtils.PATIENT_ENTERS_UNITCODE.equalsIgnoreCase(userMapping.getUnitcode())) {
                    unitCodes.add(userMapping.getUnitcode());
                }
            }
        }

        return unitCodes;
    }
}
