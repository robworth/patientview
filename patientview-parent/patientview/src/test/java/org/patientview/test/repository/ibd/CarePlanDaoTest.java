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

package org.patientview.test.repository.ibd;

import org.patientview.ibd.model.CarePlan;
import org.patientview.ibd.model.enums.Confidence;
import org.patientview.ibd.model.enums.Importance;
import org.patientview.repository.ibd.CarePlanDao;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CarePlanDaoTest extends BaseDaoTest {

    @Inject
    private CarePlanDao carePlanDao;

    @Test
    public void testAddGetCarePlan() throws Exception {
        CarePlan carePlan = getTestObject();

        carePlanDao.save(carePlan);

        assertTrue("Invalid id for new care plan", carePlan.getId() > 0);

        CarePlan checkCarePlan = carePlanDao.get(carePlan.getId());

        assertNotNull(checkCarePlan);
        assertEquals("NHS no not persisted", checkCarePlan.getNhsno(), carePlan.getNhsno());
        assertEquals("Goals not persisted", checkCarePlan.getGoals(), carePlan.getGoals());
        assertEquals("Goal to achieve not persisted", checkCarePlan.getGoalToAchieve(), carePlan.getGoalToAchieve());
        assertEquals("Importance not persisted", checkCarePlan.getImportance(), carePlan.getImportance());
        assertEquals("How to achieve not persisted", checkCarePlan.getHowToAchieveGoal(),
                carePlan.getHowToAchieveGoal());
        assertEquals("Barriers not persisted", checkCarePlan.getBarriers(), carePlan.getBarriers());
        assertEquals("What can be done not persisted", checkCarePlan.getWhatCanBeDone(), carePlan.getWhatCanBeDone());
        assertEquals("Confidence not persisted", checkCarePlan.getConfidence(), carePlan.getConfidence());
        assertEquals("Review date not persisted", checkCarePlan.getReviewDate(), carePlan.getReviewDate());

        // todo add new score stuff
    }

    @Test
    public void testGetByNhsNo() throws Exception {
        CarePlan carePlan = getTestObject();

        carePlanDao.save(carePlan);

        assertTrue("Invalid id for new care plan", carePlan.getId() > 0);

        CarePlan checkCarePlan = carePlanDao.get(carePlan.getNhsno());

        assertNotNull(checkCarePlan);
    }

    private CarePlan getTestObject() throws Exception {
        CarePlan carePlan = new CarePlan();

        carePlan.setNhsno("1234567890");
        carePlan.setGoals("Goals");
        carePlan.setGoalToAchieve("Goal");
        carePlan.setImportance(Importance.NOT_IMPORTANT_AT_ALL);
        carePlan.setHowToAchieveGoal("How to achieve goal");
        carePlan.setBarriers("Barriers");
        carePlan.setWhatCanBeDone("What can be done");
        carePlan.setConfidence(Confidence.NOT_CONFIDENT);
        carePlan.setReviewDate(new Date());

        // topdo score stuff

        return carePlan;
    }
}
