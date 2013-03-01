package com.worthsoln.repository;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.model.Unit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface UnitDao {

    Unit get(Long id);

    Unit get(String unitCode, Tenancy tenancy);

    void save(Unit unit);

    List<Unit> getAll(boolean sortByName, Tenancy tenancy);

    List<Unit> getUnitsWithUser(Tenancy tenancy);

    List<Unit> get(List<String> usersUnitCodes, Tenancy tenancy);

    List<Unit> get(List<String> usersUnitCodes, String[] notTheseUnitCodes, String[] plusTheseUnitCodes,
                   Tenancy tenancy);
}
