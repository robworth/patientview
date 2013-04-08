package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Unit;
import com.worthsoln.repository.FeatureDao;
import com.worthsoln.service.FeatureManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "featureManager")
public class FeatureManagerImpl implements FeatureManager {

    @Inject
    private FeatureDao featureDao;

    @Override
    public List<Unit> getUnitsForFeature(String feature) {
        return featureDao.getUnitsForFeature(feature);
    }
}
