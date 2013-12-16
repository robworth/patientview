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

import org.junit.Test;
import org.patientview.batch.CheckSendEmailStatusReader;
import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.EmailQueue;
import org.patientview.patientview.model.Job;
import org.patientview.patientview.model.Message;
import org.patientview.model.Specialty;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.service.EmailQueueManager;
import org.patientview.service.JobManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class CheckSendEmailStatusReaderTest extends BaseBatchTest {

    @Autowired
    private CheckSendEmailStatusReader checkSendEmailStatusReader;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private JobManager jobManager;

    @Inject
    private EmailQueueManager emailQueueManager;


    @Test
    public void testRead() throws Exception {

        if (!canRun()) {
            return;
        }
        User user1 = serviceHelpers.createUser("test 4", "tester1@test.com", "test1", "Test 1");
        User user2 = serviceHelpers.createUser("test 5", "tester2@test.com", "test2", "Test 2");
        User user3 = serviceHelpers.createUser("test 6", "tester3@test.com", "test3", "Test 3");
        List<Job> jobs = getJob();

        EmailQueue queue1 = new EmailQueue();
        queue1.setJob(jobs.get(0));
        queue1.setMessage(jobs.get(0).getMessage());
        queue1.setRecipient(user1);
        queue1.setStatus(SendEmailEnum.SUCCEEDED);
        emailQueueManager.save(queue1);

        EmailQueue queue2 = new EmailQueue();
        queue2.setJob(jobs.get(0));
        queue2.setMessage(jobs.get(0).getMessage());
        queue2.setRecipient(user2);
        queue2.setStatus(SendEmailEnum.FAILED);
        emailQueueManager.save(queue2);

        EmailQueue queue3 = new EmailQueue();
        queue3.setJob(jobs.get(1));
        queue3.setMessage(jobs.get(1).getMessage());
        queue3.setRecipient(user3);
        queue3.setStatus(SendEmailEnum.SUCCEEDED);
        emailQueueManager.save(queue3);

        checkSendEmailStatusReader.refresh();
        List<Object> checkJobList = checkSendEmailStatusReader.getList();

        assertNotNull(checkJobList);
        assertEquals("Wrong number of job list size", 2, checkJobList.size());
        assertEquals("Job 1 not update the status to Failed", SendEmailEnum.FAILED, ((Job)checkJobList.get(0)).getStatus());
        assertEquals("Job 2 not update the status to SUCCEEDED", SendEmailEnum.SUCCEEDED, ((Job)checkJobList.get(1)).getStatus());

    }

    private List<Job> getJob(){
        List<Job> jobs = new ArrayList<Job>();
        User user1 = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = serviceHelpers.createConversation("Test subject", user1, user2, true);

        Message message = serviceHelpers.createMessage(conversation, user1, user2, "This is a message", true);
        Specialty specialty = serviceHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        Job job1 = new Job();
        job1.setCreator(user1);
        job1.setMessage(message);
        job1.setSpecialty(specialty);
        job1.setStatus(SendEmailEnum.SENT);
        job1.setGroupEnum(GroupEnum.ALL_ADMINS);

        jobManager.save(job1);
        jobs.add(job1);

        Job job2 = new Job();
        job2.setCreator(user1);
        job2.setMessage(message);
        job2.setSpecialty(specialty);
        job2.setStatus(SendEmailEnum.SENT);
        job2.setGroupEnum(GroupEnum.ALL_PATIENTS);

        jobManager.save(job2);
        jobs.add(job2);

        return jobs;
    }

}
