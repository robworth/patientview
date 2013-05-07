package com.worthsoln.repository;

import com.worthsoln.patientview.model.Unit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface FeatureDao {

    List<Unit> getUnitsForFeature(String feature);
}
