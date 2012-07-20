package com.worthsoln.service;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UnitStat;

import java.util.List;

/**
 *
 *
 */
public interface UnitManager {

    Unit get(Long id);

    Unit get(String unitCode);

    List<Unit> getAll(boolean sortByName);

    List<Unit> getUnitsWithUser();

    List<Unit> getLoggedInUsersUnits();

    List<Unit> getLoggedInUsersUnits(String[] notTheseUnitCodes, String[] plusTheseUnitCodes);

    List<String> getUsersUnitCodes();

    List<UnitStat> getPatientCountsForUnit(String unitCode);

    List<UnitStat> getUnitStatsForUnit(String unitCode);
}
