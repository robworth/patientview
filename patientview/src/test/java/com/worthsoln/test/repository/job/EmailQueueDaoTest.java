package com.worthsoln.test.repository.job;

import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.repository.job.EmailQueueDao;
import com.worthsoln.repository.job.JobDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
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
        emailQueueDao.save(queue);


        assertTrue("Invalid id for EmailQueue ", queue.getId() > 0);

        EmailQueue checkEmailQueue = emailQueueDao.get(job.getId(), job.getMessage().getId(), job.getCreator().getId());

        assertNotNull(checkEmailQueue);
        assertEquals("Job not stored", checkEmailQueue.getJob(), job);
        assertEquals("Message not stored", checkEmailQueue.getMessage(), job.getMessage());
        assertEquals("User not stored", checkEmailQueue.getRecipient(), job.getCreator());
    }

    public void testGetEmailQueueList() throws Exception {
        User user1 = repositoryHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = repositoryHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        User user3 = repositoryHelpers.createUser("test 3", "tester3@test.com", "test3", "Test 3");
        Job job = getJob();

        EmailQueue queue1 = new EmailQueue();
        queue1.setJob(job);
        queue1.setMessage(job.getMessage());
        queue1.setRecipient(user1);
        queue1.setStatus(SendEmailEnum.SENDING);
        emailQueueDao.save(queue1);

        EmailQueue queue2 = new EmailQueue();
        queue2.setJob(job);
        queue2.setMessage(job.getMessage());
        queue2.setRecipient(user1);
        queue1.setStatus(SendEmailEnum.FAILED);
        emailQueueDao.save(queue2);

        EmailQueue queue3 = new EmailQueue();
        queue3.setJob(job);
        queue3.setMessage(job.getMessage());
        queue3.setRecipient(user1);
        queue3.setStatus(SendEmailEnum.SUCCEEDED);
        emailQueueDao.save(queue3);

        List<EmailQueue> checkEmailQueueList1 = emailQueueDao.getEmailQueueList();
        List<EmailQueue> checkEmailQueueList2 = emailQueueDao.getEmailQueueList(job.getId());

        assertNotNull(checkEmailQueueList1);
        assertEquals("Wrong number of job size", checkEmailQueueList1.size(), 2);
        assertFalse("EmailQueue 3 found in SendEmailEnum SUCCEEDED", checkEmailQueueList1.contains(queue3));
        assertTrue("EmailQueue 1 not found in SendEmailEnum SENDING", checkEmailQueueList1.contains(queue1));
        assertTrue("EmailQueue 2 not found in SendEmailEnum FAILED", checkEmailQueueList1.contains(queue2));

        assertNotNull(checkEmailQueueList2);
        assertEquals("Wrong number of job size", checkEmailQueueList2.size(), 1);
        assertFalse("EmailQueue 1 found in SendEmailEnum SUCCEEDED", checkEmailQueueList2.contains(queue1));
        assertFalse("EmailQueue 3 found in SendEmailEnum SUCCEEDED", checkEmailQueueList2.contains(queue3));
        assertTrue("EmailQueue 2 not found in SendEmailEnum SENDING", checkEmailQueueList2.contains(queue2));
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
        jobDao.save(job);

        return job;
    }

}
