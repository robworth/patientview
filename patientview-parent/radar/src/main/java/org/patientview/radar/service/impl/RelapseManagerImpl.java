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

import org.patientview.radar.dao.RelapseDao;
import org.patientview.radar.model.sequenced.Relapse;
import org.patientview.radar.service.RelapseManager;

import java.util.List;

public class RelapseManagerImpl implements RelapseManager {

    RelapseDao relapseDao;

    public void saveRelapse(Relapse relapse) {
        relapseDao.saveRelapse(relapse);
    }

    public void deleteRelapse(Relapse relapse) {
         relapseDao.deleteRelapse(relapse);
    }

    public Relapse getRelapse(long id) {
        return relapseDao.getRelapse(id);
    }

    public List<Relapse> getRelapsesByRadarNumber(long radarNumber) {
        return relapseDao.getRelapsesByRadarNumber(radarNumber);
    }

    public RelapseDao getRelapseDao() {
        return relapseDao;
    }

    public void setRelapseDao(RelapseDao relapseDao) {
        this.relapseDao = relapseDao;
    }
}
