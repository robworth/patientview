package org.patientview.test.batch;

import org.patientview.batch.CheckSendEmailStatusReader;
import org.patientview.patientview.model.*;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.service.EmailQueueManager;
import org.patientview.service.JobManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.patientview.test.service.BaseServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class CheckSendEmailStatusReaderTest extends BaseServiceTest {

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
