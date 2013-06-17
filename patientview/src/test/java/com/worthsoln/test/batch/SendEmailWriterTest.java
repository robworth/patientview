package com.worthsoln.test.batch;

import com.worthsoln.batch.SendEmailReader;
import com.worthsoln.batch.SendEmailWriter;
import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.GroupEnum;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import com.worthsoln.test.helpers.ServiceHelpers;
import com.worthsoln.test.service.BaseServiceTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
public class SendEmailWriterTest extends BaseServiceTest {

    @Autowired
    private SendEmailWriter sendEmailWriter;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private JobManager jobManager;

    @Inject
    private EmailQueueManager emailQueueManager;


    @Test
    public void testRead() throws Exception {

        List<Object> emailQueues = new ArrayList<Object>();
        User user1 = serviceHelpers.createUser("mailTest A", "mailTestA@test.com", "mailTestA", "mailTest A");
        User user2 = serviceHelpers.createUser("mailTest B", "mailTestB@test.com", "mailTestA", "mailTest B");
        Job job = getJob();

        EmailQueue queue1 = new EmailQueue();
        queue1.setJob(job);
        queue1.setMessage(job.getMessage());
        queue1.setRecipient(user1);
        queue1.setStatus(SendEmailEnum.SENDING);
        emailQueueManager.save(queue1);
        emailQueues.add(queue1);

        EmailQueue queue2 = new EmailQueue();
        queue2.setJob(job);
        queue2.setMessage(job.getMessage());
        queue2.setRecipient(user2);
        queue2.setStatus(SendEmailEnum.FAILED);
        emailQueueManager.save(queue2);
        emailQueues.add(queue2);

        sendEmailWriter.write(emailQueues);

    }

    private Job getJob(){
        User adminUser = serviceHelpers.createUser("adminUser", "tester1@test.com", "test1", "Admin User");
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = serviceHelpers.createConversation("Test subject", adminUser, user2, true);

        Message message = serviceHelpers.createMessage(conversation, adminUser, user2, "This is a message", true);
        Specialty specialty = serviceHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        Job job = new Job();
        job.setCreator(adminUser);
        job.setMessage(message);
        job.setSpecialty(specialty);
        job.setStatus(SendEmailEnum.PENDING);
        job.setGroupEnum(GroupEnum.ALL_ADMINS);
        jobManager.save(job);

        return job;
    }

}
