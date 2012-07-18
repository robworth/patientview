package com.worthsoln.repository;

import com.worthsoln.patientview.model.Unit;

import java.util.List;

/**
 *
 */
public interface UnitDao {

    Unit get(Long id);

    List<Unit> getAll(boolean sortByName);

    List<Unit> get(List<String> usersUnitCodes);

    List<Unit> get(List<String> usersUnitCodes, String[] notTheseUnitCodes, String[] plusTheseUnitCodes);
}
