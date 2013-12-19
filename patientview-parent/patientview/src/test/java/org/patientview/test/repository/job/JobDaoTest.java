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

package org.patientview.test.repository.job;

import org.patientview.model.Specialty;
import org.patientview.patientview.model.*;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.repository.job.JobDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class JobDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private JobDao jobDao;

    @Test
    public void testAddGetJob() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        Message message = repositoryHelpers.createMessage(conversation, user1, user2, "This is a message", true);
        Specialty specialty = repositoryHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        Job job1 = new Job();
        job1.setCreator(user1);
        job1.setMessage(message);
        job1.setSpecialty(specialty);
        job1.setStatus(SendEmailEnum.PENDING);
        job1.setGroupEnum(GroupEnum.ALL_ADMINS);
        jobDao.save(job1);

        Job job2 = new Job();
        job2.setCreator(user2);
        job2.setMessage(message);
        job2.setSpecialty(specialty);
        job2.setStatus(SendEmailEnum.FAILED);
        job2.setGroupEnum(GroupEnum.ALL_PATIENTS);
        jobDao.save(job2);


        assertTrue("Invalid id for Job 1 ", job1.getId() > 0);
        assertTrue("Invalid id for Job 2 ", job2.getId() > 0);

        List<Job> checkJobList = jobDao.getJobList(SendEmailEnum.PENDING);

        assertNotNull(checkJobList);
        assertEquals("Wrong number of job size", checkJobList.size(), 1);
        assertFalse("Job 2 found in SendEmailEnum PENDING", checkJobList.contains(job2));
        assertTrue("Job 1 not found in SendEmailEnum PENDING", checkJobList.contains(job1));
        assertEquals("Specialty not stored", checkJobList.get(0).getSpecialty(), specialty);
        assertEquals("Message not stored", checkJobList.get(0).getMessage(), message);
        assertEquals("User not stored", checkJobList.get(0).getCreator(), user1);
    }

}
