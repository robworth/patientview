/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

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
