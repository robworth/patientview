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

import org.patientview.radar.dao.ClinicalDataDao;
import org.patientview.radar.model.Phenotype;
import org.patientview.radar.model.sequenced.ClinicalData;
import org.patientview.radar.service.ClinicalDataManager;

import java.util.List;

public class ClinicalDataManagerImpl implements ClinicalDataManager {

    private ClinicalDataDao clinicalDataDao;

    public void saveClinicalDate(ClinicalData clinicalData) {
        clinicalDataDao.saveClinicalDate(clinicalData);
    }

    public ClinicalData getClinicalData(long id) {
        return clinicalDataDao.getClinicalData(id);
    }

    public List<ClinicalData> getClinicalDataByRadarNumber(long radarNumber) {
        return clinicalDataDao.getClinicalDataByRadarNumber(radarNumber);
    }

    public ClinicalData getFirstClinicalDataByRadarNumber(long radarNumber) {
        return clinicalDataDao.getFirstClinicalDataByRadarNumber(radarNumber);
    }

    public Phenotype getPhenotype(long id) {
        return clinicalDataDao.getPhenotype(id);
    }

    public List<Phenotype> getPhenotypes() {
        return clinicalDataDao.getPhenotypes();
    }

    public ClinicalDataDao getClinicalDataDao() {
        return clinicalDataDao;
    }

    public void setClinicalDataDao(ClinicalDataDao clinicalDataDao) {
        this.clinicalDataDao = clinicalDataDao;
    }
}
