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

import org.patientview.model.Specialty;
import org.patientview.patientview.model.*;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.service.EmailQueueManager;
import org.patientview.service.JobManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class EmailQueueManagerTest extends BaseServiceTest {

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private JobManager jobManager;

    @Inject
    private EmailQueueManager emailQueueManager;


    @Test
    public void testAddGetEmailQueue() throws Exception {
        Job job = getJob();

        EmailQueue queue = new EmailQueue();
        queue.setJob(job);
        queue.setMessage(job.getMessage());
        queue.setRecipient(job.getCreator());
        queue.setCreated(new Date());
        queue.setStatus(SendEmailEnum.PENDING);
        emailQueueManager.save(queue);


        assertTrue("Invalid id for EmailQueue ", queue.getId() > 0);

        EmailQueue checkEmailQueue = emailQueueManager.get(job.getId(), job.getMessage().getId(), job.getCreator().getId());

        assertNotNull(checkEmailQueue);
        assertEquals("Job not stored", checkEmailQueue.getJob(), job);
        assertEquals("Message not stored", checkEmailQueue.getMessage(), job.getMessage());
        assertEquals("User not stored", checkEmailQueue.getRecipient(), job.getCreator());
    }

    @Test
    public void testGetEmailQueueList() throws Exception {
        User user1 = serviceHelpers.createUser("test 4", "tester1@test.com", "test1", "Test 1");
        User user2 = serviceHelpers.createUser("test 5", "tester2@test.com", "test2", "Test 2");
        User user3 = serviceHelpers.createUser("test 6", "tester3@test.com", "test3", "Test 3");
        Job job = getJob();

        EmailQueue queue1 = new EmailQueue();
        queue1.setJob(job);
        queue1.setMessage(job.getMessage());
        queue1.setRecipient(user1);
        queue1.setStatus(SendEmailEnum.SENDING);
        emailQueueManager.save(queue1);

        EmailQueue queue2 = new EmailQueue();
        queue2.setJob(job);
        queue2.setMessage(job.getMessage());
        queue2.setRecipient(user2);
        queue2.setStatus(SendEmailEnum.FAILED);
        emailQueueManager.save(queue2);

        EmailQueue queue3 = new EmailQueue();
        queue3.setJob(job);
        queue3.setMessage(job.getMessage());
        queue3.setRecipient(user3);
        queue3.setStatus(SendEmailEnum.SUCCEEDED);
        emailQueueManager.save(queue3);

        List<EmailQueue> checkEmailQueueList1 = emailQueueManager.getEmailQueueList();
        List<EmailQueue> checkEmailQueueList2 = emailQueueManager.getEmailQueueList(job.getId());

        assertNotNull(checkEmailQueueList1);
        assertEquals("Wrong number of job list 1 size", checkEmailQueueList1.size(), 2);
        assertFalse("EmailQueue 3 found in SendEmailEnum SENDING", checkEmailQueueList1.contains(queue3));
        assertTrue("EmailQueue 1 not found in SendEmailEnum SENDING", checkEmailQueueList1.contains(queue1));
        assertTrue("EmailQueue 2 not found in SendEmailEnum FAILED", checkEmailQueueList1.contains(queue2));

        assertNotNull(checkEmailQueueList2);
        assertEquals("Wrong number of job list 2 size", checkEmailQueueList2.size(), 3);
        assertTrue("EmailQueue 1 not found in SendEmailEnum SUCCEEDED", checkEmailQueueList2.contains(queue1));
        assertTrue("EmailQueue 3 not found in SendEmailEnum FAILED", checkEmailQueueList2.contains(queue3));
        assertTrue("EmailQueue 2 not found in SendEmailEnum SUCCEEDED", checkEmailQueueList2.contains(queue2));
    }

    private Job getJob(){
        User user1 = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = serviceHelpers.createConversation("Test subject", user1, user2, true);

        Message message = serviceHelpers.createMessage(conversation, user1, user2, "This is a message", true);
        Specialty specialty = serviceHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        Job job = new Job();
        job.setCreator(user1);
        job.setMessage(message);
        job.setSpecialty(specialty);
        job.setStatus(SendEmailEnum.PENDING);
        job.setGroupEnum(GroupEnum.ALL_ADMINS);
        jobManager.save(job);

        return job;
    }
}
