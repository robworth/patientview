package org.patientview.radar.service.impl;

import org.patientview.radar.dao.PathologyDao;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.model.sequenced.Pathology;
import org.patientview.radar.service.PathologyManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathologyManagerImpl implements PathologyManager {

    private PathologyDao pathologyDao;

    public void savePathology(Pathology pathology) throws InvalidModelException {
        // validate
        List<String> errors = new ArrayList<String>();

        int total = pathology.getTotalNumber() != null ? pathology.getTotalNumber() : 0;
        int sum = 0;
        for (Integer value : Arrays.asList(pathology.getNumberSclerosed(), pathology.getNumberSegmentallySclerosed(),
                pathology.getNumberCellularCrescents(), pathology.getNumberFibrousCrescents(),
                pathology.getNumberEndocapillaryHypercelluarity(), pathology.getNumberFibrinoidNecrosis())) {

            sum += value != null ? value : 0;
        }

        if (sum > total) {
            errors.add(TOTAL_ERROR);
        }

        if (!errors.isEmpty()) {
            InvalidModelException exception = new InvalidModelException("Pathology model is not valid");
            exception.setErrors(errors);
            throw exception;
        }

        pathologyDao.savePathology(pathology);
    }

    public Pathology getPathology(long id) {
        return pathologyDao.getPathology(id);
    }

    public List<Pathology> getPathologyByRadarNumber(long radarNumber) {
        return pathologyDao.getPathologyByRadarNumber(radarNumber);
    }

    public PathologyDao getPathologyDao() {
        return pathologyDao;
    }

    public void setPathologyDao(PathologyDao pathologyDao) {
        this.pathologyDao = pathologyDao;
    }
}
