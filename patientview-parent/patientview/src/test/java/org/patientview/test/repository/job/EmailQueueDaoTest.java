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
import org.patientview.repository.job.EmailQueueDao;
import org.patientview.repository.job.JobDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.patientview.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class EmailQueueDaoTest extends BaseDaoTest {

    @Inject
    private RepositoryHelpers repositoryHelpers;

    @Inject
    private EmailQueueDao emailQueueDao;

    @Inject
    private JobDao jobDao;

    @Test
    public void testAddGetEmailQueue() throws Exception {
        Job job = getJob();

        EmailQueue queue = new EmailQueue();
        queue.setJob(job);
        queue.setMessage(job.getMessage());
        queue.setRecipient(job.getCreator());
        queue.setCreated(new Date());
        queue.setStatus(SendEmailEnum.PENDING);
        emailQueueDao.save(queue);


        assertTrue("Invalid id for EmailQueue ", queue.getId() > 0);

        EmailQueue checkEmailQueue = emailQueueDao.get(job.getId(), job.getMessage().getId(), job.getCreator().getId());

        assertNotNull(checkEmailQueue);
        assertEquals("Job not stored", checkEmailQueue.getJob(), job);
        assertEquals("Message not stored", checkEmailQueue.getMessage(), job.getMessage());
        assertEquals("User not stored", checkEmailQueue.getRecipient(), job.getCreator());
    }

    @Test
    public void testGetEmailQueueList() throws Exception {
        User user1 = repositoryHelpers.createUser("test 4", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 5", "tester2@test.com", "test2", "Test 2");
        User user3 = repositoryHelpers.createUser("test 6", "tester3@test.com", "test3", "Test 3");
        Job job = getJob();

        EmailQueue queue1 = new EmailQueue();
        queue1.setJob(job);
        queue1.setMessage(job.getMessage());
        queue1.setRecipient(user1);
        queue1.setStatus(SendEmailEnum.SENDING);
        queue1.setCreated(new Date());
        emailQueueDao.save(queue1);

        EmailQueue queue2 = new EmailQueue();
        queue2.setJob(job);
        queue2.setMessage(job.getMessage());
        queue2.setRecipient(user2);
        queue2.setStatus(SendEmailEnum.FAILED);
        queue2.setCreated(new Date());
        emailQueueDao.save(queue2);

        EmailQueue queue3 = new EmailQueue();
        queue3.setJob(job);
        queue3.setMessage(job.getMessage());
        queue3.setRecipient(user3);
        queue3.setStatus(SendEmailEnum.SUCCEEDED);
        queue3.setCreated(new Date());
        emailQueueDao.save(queue3);

        List<EmailQueue> checkEmailQueueList1 = emailQueueDao.getEmailQueueList();
        List<EmailQueue> checkEmailQueueList2 = emailQueueDao.getEmailQueueList(job.getId());

        assertNotNull(checkEmailQueueList1);
        assertEquals("Wrong number of job list 1 size", checkEmailQueueList1.size(), 2);
        assertFalse("EmailQueue 3 found in SendEmailEnum SENDING", checkEmailQueueList1.contains(queue3));
        assertTrue("EmailQueue 1 not found in SendEmailEnum SENDING", checkEmailQueueList1.contains(queue1));
        assertTrue("EmailQueue 2 not found in SendEmailEnum FAILED", checkEmailQueueList1.contains(queue2));

        assertNotNull(checkEmailQueueList2);
        assertEquals("Wrong number of job list 2 size", checkEmailQueueList2.size(), 3);
        assertTrue("EmailQueue 1 found in SendEmailEnum SUCCEEDED", checkEmailQueueList2.contains(queue1));
        assertTrue("EmailQueue 2 found in SendEmailEnum FAILED", checkEmailQueueList2.contains(queue1));
        assertTrue("EmailQueue 3 found in SendEmailEnum SUCCEEDED", checkEmailQueueList2.contains(queue3));
    }

    private Job getJob(){
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = repositoryHelpers.createConversation("Test subject", user1, user2, true);

        Message message = repositoryHelpers.createMessage(conversation, user1, user2, "This is a message", true);
        Specialty specialty = repositoryHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        Job job = new Job();
        job.setCreator(user1);
        job.setMessage(message);
        job.setSpecialty(specialty);
        job.setStatus(SendEmailEnum.PENDING);
        job.setGroupEnum(GroupEnum.ALL_ADMINS);
        jobDao.save(job);

        return job;
    }

}
