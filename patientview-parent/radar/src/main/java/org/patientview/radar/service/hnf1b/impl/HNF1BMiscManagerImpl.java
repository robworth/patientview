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

package org.patientview.radar.service.hnf1b.impl;

import org.patientview.radar.dao.hnf1b.HNF1BMiscDao;
import org.patientview.radar.model.hnf1b.HNF1BMisc;
import org.patientview.radar.service.hnf1b.HNF1BMiscManager;

public class HNF1BMiscManagerImpl implements HNF1BMiscManager {

    private HNF1BMiscDao hnf1BMiscDao;

    public void save(HNF1BMisc hnf1BMisc) {
        hnf1BMiscDao.save(hnf1BMisc);
    }

    public HNF1BMisc get(Long radarNo) {
        return hnf1BMiscDao.get(radarNo);
    }

    public void setHnf1BMiscDao(HNF1BMiscDao hnf1BMiscDao) {
        this.hnf1BMiscDao = hnf1BMiscDao;
    }
}
