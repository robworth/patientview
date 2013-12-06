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

package org.patientview.test.repository;

import org.junit.Test;
import org.patientview.patientview.model.DiabetesCarePlan;
import org.patientview.repository.DiabetesCarePlanDao;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test UserMappingDao and UserDao
 */
public class DiabetesCarePlanDaoTest extends BaseDaoTest {

    @Inject
    private DiabetesCarePlanDao diabetesCarePlanDao;

    @Test
    public void testGetCarePlan() {

        DiabetesCarePlan plan = new DiabetesCarePlan();
        plan.setBarriers("setBarriers");
        plan.setConfidenceId(1L);
        plan.setGoals("setGoals");
        plan.setImportanceId(2L);
        plan.setWhatCanBeDone("setWhatCanBeDone");
        plan.setNhsno("123");
        plan.setGoalToAchieve("setGoalToAchieve");
        plan.setHowToAchieveGoal("setHowToAchieveGoal");
        plan.setOtherAreasToDiscuss("setOtherAreasToDiscuss");
        Date today = new Date();
        plan.setReviewDate(today);
        diabetesCarePlanDao.save(plan);

        DiabetesCarePlan plan2 = diabetesCarePlanDao.get("123");
        assertEquals(plan2.getBarriers(), plan.getBarriers());
        assertEquals(plan2.getConfidenceId(), plan.getConfidenceId());
        assertEquals(plan2.getGoals(), plan.getGoals());
        assertEquals(plan2.getImportanceId(), plan.getImportanceId());
        assertEquals(plan2.getWhatCanBeDone(), plan.getWhatCanBeDone());
        assertEquals(plan2.getNhsno(), plan.getNhsno());
        assertEquals(plan2.getGoalToAchieve(), plan.getGoalToAchieve());
        assertEquals(plan2.getHowToAchieveGoal(), plan.getHowToAchieveGoal());
        assertEquals(plan2.getOtherAreasToDiscuss(), plan.getOtherAreasToDiscuss());
        assertEquals(plan2.getReviewDate(), plan.getReviewDate());
    }


}
