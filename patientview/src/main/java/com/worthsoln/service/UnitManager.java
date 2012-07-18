package com.worthsoln.service;

import com.worthsoln.patientview.model.Unit;

import java.util.List;

/**
 *
 *
 */
public interface UnitManager {

    Unit get(Long id);

    List<Unit> getAll(boolean sortByName);

    List<Unit> getLoggedInUsersUnits();

    List<Unit> getLoggedInUsersUnits(String[] notTheseUnitCodes, String[] plusTheseUnitCodes);

    List<String> getUsersUnitCodes();
}
