package org.patientview.test.quartz;

import org.patientview.patientview.model.EmailQueue;
import org.patientview.quartz.SendEmailJobQuartzScheduler;
import org.patientview.service.EmailQueueManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:test-context.xml"})
public class SendEmailJobQuartzSchedulerTest {

    @Autowired
    private SendEmailJobQuartzScheduler sendEmailJobQuartzScheduler;

    @Inject
    private EmailQueueManager emailQueueManager;

    @Test
    public void testExecute() throws Exception {

        sendEmailJobQuartzScheduler.execute();
        List<EmailQueue> emailQueues = emailQueueManager.getEmailQueueList();

        assertEquals("Wrong number of list size", 0, emailQueues.size());

    }

}
