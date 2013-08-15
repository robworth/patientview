package org.patientview.test.batch;

import org.patientview.batch.CheckSendEmailStatusWriter;
import org.patientview.patientview.model.*;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.service.JobManager;
import org.patientview.test.helpers.ServiceHelpers;
import org.patientview.test.service.BaseServiceTest;
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
public class CheckSendEmailStatusWriterTest extends BaseServiceTest {

    @Autowired
    private CheckSendEmailStatusWriter checkSendEmailStatusWriter;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private JobManager jobManager;

    @Test
    public void testWrite() throws Exception {

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
