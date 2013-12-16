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

package org.patientview.test.batch;

import org.patientview.batch.CheckSendEmailStatusWriter;
import org.patientview.model.Specialty;
import org.patientview.patientview.model.*;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.service.JobManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class CheckSendEmailStatusWriterTest extends BaseBatchTest {

    @Autowired
    private CheckSendEmailStatusWriter checkSendEmailStatusWriter;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private JobManager jobManager;

    @Test
    public void testWrite() throws Exception {
        if (!canRun()) {
            return;
        }

        List<Object> jobs = new ArrayList<Object>();
        User user1 = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = serviceHelpers.createConversation("Test subject", user1, user2, true);

        Message message = serviceHelpers.createMessage(conversation, user1, user2, "This is a message", true);
        Specialty specialty = serviceHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        Job job1 = new Job();
        job1.setCreator(user1);
        job1.setMessage(message);
        job1.setSpecialty(specialty);
        job1.setStatus(SendEmailEnum.SUCCEEDED);
        job1.setGroupEnum(GroupEnum.ALL_ADMINS);

        jobManager.save(job1);
        jobs.add(job1);

        Job job2 = new Job();
        job2.setCreator(user2);
        job2.setMessage(message);
        job2.setSpecialty(specialty);
        job2.setStatus(SendEmailEnum.FAILED);
        job2.setGroupEnum(GroupEnum.ALL_PATIENTS);

        jobManager.save(job2);
        jobs.add(job2);

        checkSendEmailStatusWriter.write(jobs);

        List<Job> checkJobs1 = jobManager.getJobList(SendEmailEnum.SUCCEEDED);
        List<Job> checkJobs2 = jobManager.getJobList(SendEmailEnum.FAILED);
        List<Job> checkJobs3 = jobManager.getJobList(SendEmailEnum.PENDING);

        assertEquals("Wrong number of job list 1", 1, checkJobs1.size());
        assertEquals("Wrong number of job list 2", 1, checkJobs2.size());
        assertEquals("Wrong number of job list 3", 0, checkJobs3.size());

        assertEquals("Message not found", checkJobs1.get(0).getMessage(), message);
        assertEquals("Message not found", checkJobs2.get(0).getMessage(), message);
        assertEquals("User 1 not found", checkJobs1.get(0).getCreator(), user1);
        assertEquals("User 2 not found", checkJobs2.get(0).getCreator(), user2);
        assertEquals("Specialty not found", checkJobs1.get(0).getSpecialty(),  specialty);
        assertEquals("Specialty not found", checkJobs2.get(0).getSpecialty(), specialty);

    }

}
