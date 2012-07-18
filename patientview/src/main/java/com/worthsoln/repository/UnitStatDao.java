package com.worthsoln.repository;

import com.worthsoln.patientview.model.UnitStat;

import java.util.List;

/**
 *
 */
public interface UnitStatDao {

    List<UnitStat> get(String unitCode);
}
