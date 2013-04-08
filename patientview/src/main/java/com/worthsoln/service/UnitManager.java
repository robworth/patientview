package com.worthsoln.service;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UnitStat;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface UnitManager {

    Unit get(Long id);

    Unit get(String unitCode);

    void save(Unit unit);

    List<Unit> getAll(boolean sortByName);

    List<Unit> getUnitsWithUser();

    List<Unit> getLoggedInUsersUnits();

    List<Unit> getUsersUnits(User user);

    List<Unit> getLoggedInUsersUnits(String[] notTheseUnitCodes, String[] plusTheseUnitCodes);

    List<String> getUsersUnitCodes(User user);

    List<UnitStat> getPatientCountsForUnit(String unitCode);

    List<UnitStat> getUnitStatsForUnit(String unitCode);

    List<UnitAdmin> getUnitUsers(String unitcode);
}
