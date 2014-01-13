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

package org.patientview.radar.test.dao.alport;

import org.apache.commons.lang.time.DateUtils;
import org.patientview.radar.dao.alport.DeafnessDao;
import org.patientview.radar.model.alport.Deafness;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DeafnessDaoTest extends BaseDaoTest {

    @Autowired
    private DeafnessDao deafnessDao;

    @Test
    public void testAddGetDeafness() throws Exception {
        Deafness deafness = getTestObject();

        deafnessDao.save(deafness);

        assertTrue("Invalid id for new deafness", deafness.getId() > 0);

        Deafness checkDeafness = deafnessDao.get(deafness.getId());

        assertNotNull(checkDeafness);
        assertEquals("Radar no not persisted", deafness.getRadarNo(), checkDeafness.getRadarNo());
        assertEquals("Evidence of deafness not persisted", deafness.getEvidenceOfDeafness(),
                checkDeafness.getEvidenceOfDeafness());
        assertTrue("Date problem first noticed not persisted",
                DateUtils.isSameDay(deafness.getDateProblemFirstNoticed(),
                checkDeafness.getDateProblemFirstNoticed()));
        assertTrue("Date started using hearing aid not persisted",
                DateUtils.isSameDay(deafness.getDateStartedUsingHearingAid(),
                        checkDeafness.getDateStartedUsingHearingAid()));

    }

    private Deafness getTestObject() {
        Deafness deafness = new Deafness();
        deafness.setRadarNo(1L);
        deafness.setEvidenceOfDeafness(Deafness.EvidenceOfDeafness.YES_HEARING_AID_NEEDED);
        deafness.setDateProblemFirstNoticed(new Date());
        deafness.setDateStartedUsingHearingAid(new Date());
        return deafness;
    }
}
