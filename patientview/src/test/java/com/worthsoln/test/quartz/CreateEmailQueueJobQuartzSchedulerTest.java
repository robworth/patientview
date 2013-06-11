package com.worthsoln.test.quartz;

import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.GroupEnum;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.quartz.CreateEmailQueueJobQuartzScheduler;
import com.worthsoln.repository.SpecialtyDao;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.test.helpers.ServiceHelpers;
import com.worthsoln.test.service.BaseServiceTest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml", "classpath:test-context.xml"})
public class CreateEmailQueueJobQuartzSchedulerTest {

    @Autowired
    private CreateEmailQueueJobQuartzScheduler createEmailQueueJobQuartzScheduler;

    @Inject
    private EmailQueueManager emailQueueManager;

    @Test
    public void testExecute() throws Exception {

        createEmailQueueJobQuartzScheduler.execute();
        List<EmailQueue> emailQueues = emailQueueManager.getEmailQueueList();

        assertEquals("Wrong number of list size", 0, emailQueues.size());

    }

}
