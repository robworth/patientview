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
