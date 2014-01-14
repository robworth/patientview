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

import org.patientview.radar.dao.alport.GeneticsDao;
import org.patientview.radar.model.Genetics;
import org.patientview.radar.test.dao.BaseDaoTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class GeneticsDaoTest extends BaseDaoTest {

    @Autowired
    private GeneticsDao geneticsDao;

    @Test
    public void testAddGetGenetics() throws Exception {
        Genetics genetics = getTestObject();

        geneticsDao.save(genetics);

        assertTrue("Invalid id for new genetics", genetics.getId() > 0);

        Genetics checkGenetics = geneticsDao.get(genetics.getId());

        assertNotNull(checkGenetics);
        assertEquals("Radar no not persisted", checkGenetics.getRadarNo(), genetics.getRadarNo());
        assertEquals("Tests done not persisted", checkGenetics.getTestsDone(), genetics.getTestsDone());
        assertEquals("Labs where tests were done not persisted", checkGenetics.getLabWhereTestWasDone(),
                genetics.getLabWhereTestWasDone());
        assertEquals("Tests done by not persisted", checkGenetics.getTestDoneOn(), genetics.getTestDoneOn());
        assertEquals("Reference no not persisted", checkGenetics.getReferenceNumber(), genetics.getReferenceNumber());
        assertEquals("What results showed not persisted", checkGenetics.getWhatResultsShowed(),
                genetics.getWhatResultsShowed());
        assertEquals("Key evidence not persisted", checkGenetics.getKeyEvidence(), genetics.getKeyEvidence());
    }

    private Genetics getTestObject() {
        Genetics genetics = new Genetics();
        genetics.setRadarNo(1L);
        genetics.setTestsDone(Genetics.TestsDone.NO);
        genetics.setLabWhereTestWasDone("Test where test was done");
        genetics.setTestDoneOn("Test tests done on");
        genetics.setReferenceNumber("1234");
        genetics.setWhatResultsShowed("Test what results showed");
        genetics.setKeyEvidence("Test key evidence");
        return genetics;
    }
}
