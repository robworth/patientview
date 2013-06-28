package org.patientview.test.batch;

import org.patientview.batch.CreateEmailQueueWriter;
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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class CreateEmailQueueWriterTest extends BaseServiceTest {

    @Autowired
    private CreateEmailQueueWriter createEmailQueueWriter;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private JobManager jobManager;

    @Inject
    private EmailQueueManager emailQueueManager;


    @Test
    public void testWrite() throws Exception {

        List<Object> emailQueues = new ArrayList<Object>();
        Specialty specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        User adminUser = serviceHelpers.createUserWithMapping("adminuser", "test@admin.com", "p", "Admin", "unitA", "nhs1", specialty1);
        User user1 = serviceHelpers.createUserWithMapping("testname1", "test1@admin.com", "p", "test1", "unitA", "nhstest1", specialty1);
        User user2 = serviceHelpers.createUserWithMapping("testname2", "test2@admin.com", "p", "test2", "unitA", "nhstest2", specialty1);

        Conversation conversation = serviceHelpers.createConversation("Test subject", user1, user2, true);
        Message message = serviceHelpers.createMessage(conversation, user1, user2, "This is a message", true);

        // Add SpecialtyUserRole
        serviceHelpers.createSpecialtyUserRole(specialty1, adminUser, "unitadmin");
        serviceHelpers.createSpecialtyUserRole(specialty1, user1, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty1, user2, "patient");


        Job job = new Job();
        job.setCreator(adminUser);
        job.setGroupEnum(GroupEnum.ALL_PATIENTS);
        job.setSpecialty(specialty1);
        job.setCreated(new Date());
        job.setMessage(message);
        job.setStatus(SendEmailEnum.PENDING);
        jobManager.save(job);

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
        queue2.setStatus(SendEmailEnum.SENDING);
        emailQueueManager.save(queue2);

        emailQueues.add(queue1);
        emailQueues.add(queue2);

        createEmailQueueWriter.write(emailQueues);

        List<EmailQueue> checkEmailQueues = emailQueueManager.getEmailQueueList();
        assertEquals("Wrong number of EmailQueue list", 2, checkEmailQueues.size());

        assertEquals("Job not stored", checkEmailQueues.get(0).getJob().getId(), job.getId());
        assertEquals("Job not stored", checkEmailQueues.get(1).getJob().getId(), job.getId());
        assertEquals("Message not stored", checkEmailQueues.get(0).getMessage().getId(), message.getId());
        assertEquals("Message not stored", checkEmailQueues.get(1).getMessage().getId(), message.getId());
        assertEquals("User 1 not stored", checkEmailQueues.get(0).getRecipient().getId(), user1.getId());
        assertEquals("User 2 not stored", checkEmailQueues.get(1).getRecipient().getId(), user2.getId());

    }

}
