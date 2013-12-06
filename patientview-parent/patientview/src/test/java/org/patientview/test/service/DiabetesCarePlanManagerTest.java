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

package org.patientview.test.service;

import org.junit.Before;
import org.junit.Test;
import org.patientview.patientview.model.DiabetesCarePlan;
import org.patientview.model.Specialty;
import org.patientview.patientview.model.User;
import org.patientview.service.DiabetesCarePlanManager;
import org.patientview.test.helpers.SecurityHelpers;
import org.patientview.test.helpers.ServiceHelpers;

import javax.inject.Inject;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * These tests require an admin adminUser to be logged into a specialty
 */
public class DiabetesCarePlanManagerTest extends BaseServiceTest {

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private SecurityHelpers securityHelpers;

    @Inject
    private DiabetesCarePlanManager diabetesCarePlanManager;


    @Before
    public void setupSystem() {
    }

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
        diabetesCarePlanManager.saveCarePlan(plan);

        DiabetesCarePlan plan2 = diabetesCarePlanManager.getCarePlan("123");
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

    @Test
    public void testGetCarePlanWithUser() {
        Specialty specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        User adminUser = serviceHelpers.createUserWithMapping("Username", "username@test.com", "pass", "Test User", "unit1", "123", specialty1);

        serviceHelpers.createSpecialtyUserRole(specialty1, adminUser, "patient");

        securityHelpers.loginAsUser(adminUser.getUsername(), specialty1);

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
        diabetesCarePlanManager.saveCarePlan(plan);

        DiabetesCarePlan plan2 = diabetesCarePlanManager.getCarePlan(adminUser);
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
