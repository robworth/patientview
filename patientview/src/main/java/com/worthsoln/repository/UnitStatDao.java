package com.worthsoln.repository;

import com.worthsoln.patientview.model.UnitStat;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface UnitStatDao {

    List<UnitStat> get(String unitCode);

    void save(UnitStat unitStat);
}
