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

import org.patientview.radar.dao.LabDataDao;
import org.patientview.radar.model.sequenced.LabData;
import org.patientview.radar.service.LabDataManager;

import java.util.List;


public class LabDataManagerImpl implements LabDataManager {

    private LabDataDao labDataDao;

    public void saveLabData(LabData labData) {
       labDataDao.saveLabData(labData);
    }

    public LabData getLabData(long id) {
        return labDataDao.getLabData(id);
    }

    public List<LabData> getLabDataByRadarNumber(long id) {
        return labDataDao.getLabDataByRadarNumber(id);
    }

    public LabData getFirstLabDataByRadarNumber(Long id) {
        return labDataDao.getFirstLabDataByRadarNumber(id);
    }

    public LabDataDao getLabDataDao() {
        return labDataDao;
    }

    public void setLabDataDao(LabDataDao labDataDao) {
        this.labDataDao = labDataDao;
    }
}
