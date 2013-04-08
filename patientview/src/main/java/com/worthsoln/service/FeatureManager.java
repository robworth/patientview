package com.worthsoln.service;

import com.worthsoln.patientview.model.Unit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface FeatureManager {

    List<Unit> getUnitsForFeature(String feature);
}
