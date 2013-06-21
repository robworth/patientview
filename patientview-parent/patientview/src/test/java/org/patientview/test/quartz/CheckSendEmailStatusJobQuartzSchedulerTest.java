package org.patientview.test.quartz;

import org.patientview.patientview.model.Job;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.quartz.CheckSendEmailStatusJobQuartzScheduler;
import org.patientview.service.JobManager;
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
public class CheckSendEmailStatusJobQuartzSchedulerTest {

    @Autowired
    private CheckSendEmailStatusJobQuartzScheduler checkSendEmailStatusJobQuartzScheduler;

    @Inject
    private JobManager jobManager;

    @Test
    public void testExecute() throws Exception {

        checkSendEmailStatusJobQuartzScheduler.execute();
        List<Job> jobs1 = jobManager.getJobList(SendEmailEnum.SUCCEEDED);
        List<Job> jobs2 = jobManager.getJobList(SendEmailEnum.FAILED);
        List<Job> jobs3 = jobManager.getJobList(SendEmailEnum.SENDING);
        List<Job> jobs4 = jobManager.getJobList(SendEmailEnum.SENT);

        assertEquals("Wrong number of list 1 size", 0, jobs1.size());
        assertEquals("Wrong number of list 2 size", 0, jobs2.size());
        assertEquals("Wrong number of list 3 size", 0, jobs3.size());
        assertEquals("Wrong number of list 4 size", 0, jobs4.size());

    }

}
