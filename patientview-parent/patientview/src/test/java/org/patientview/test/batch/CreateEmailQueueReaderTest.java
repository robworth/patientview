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

package org.patientview.test.batch;

import org.junit.Before;
import org.junit.Test;
import org.patientview.batch.CreateEmailQueueReader;
import org.patientview.patientview.model.Conversation;
import org.patientview.patientview.model.EmailQueue;
import org.patientview.patientview.model.Job;
import org.patientview.patientview.model.Message;
import org.patientview.model.Specialty;
import org.patientview.model.Unit;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.repository.messaging.MessageDao;
import org.patientview.service.EmailQueueManager;
import org.patientview.service.JobManager;
import org.patientview.service.UnitManager;
import org.patientview.test.helpers.SecurityHelpers;
import org.patientview.test.helpers.ServiceHelpers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class CreateEmailQueueReaderTest extends BaseBatchTest {

    @Autowired
    private CreateEmailQueueReader createEmailQueueReader;

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private JobManager jobManager;

    @Inject
    private MessageDao messageDao;

    @Inject
    private UnitManager unitManager;

    @Inject
    private EmailQueueManager emailQueueManager;

    @Inject
    private SecurityHelpers securityHelpers;

    private User adminUser, user1, user2;
    private Specialty specialty;
    private Message message;

    @Before
    public void setup() {
        specialty = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        adminUser = serviceHelpers.createUserWithMapping("adminuser", "test@admin.com", "p", "Admin", "UNITA", "nhs1", specialty);
        user1 = serviceHelpers.createUserWithMapping("testname1", "test1@admin.com", "p", "test1", "UNITA", "nhstest1", specialty);
        user2 = serviceHelpers.createUserWithMapping("testname2", "test2@admin.com", "p", "test2", "UNITA", "nhstest2", specialty);
        User user3 = serviceHelpers.createUserWithMapping("testname3-GP", "test3@admin.com", "p", "test3", "UNITA", "nhstest3", specialty);
        User user4 = serviceHelpers.createUserWithMapping("testname4", "test4@admin.com", "p", "test4", "unitB", "nhstest4", specialty);

        Conversation conversation = serviceHelpers.createConversation("Test subject", user1, user2, true);
        message = serviceHelpers.createMessage(conversation, user1, user2, "This is a message", true);

        // Add SpecialtyUserRole
        serviceHelpers.createSpecialtyUserRole(specialty, adminUser, "unitadmin");
        serviceHelpers.createSpecialtyUserRole(specialty, user1, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty, user2, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty, user3, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty, user4, "patient");

        securityHelpers.loginAsUser(adminUser.getUsername());

        Unit unitRm301 = new Unit();
        unitRm301.setUnitcode("UNITA");
        unitRm301.setName("RM301: RUNNING MAN TEST UNIT");
        unitRm301.setShortname("RM301");
        unitRm301.setRenaladminemail("renaladmin@mailinator.com");
        unitRm301.setSpecialty(specialty);
        unitManager.save(unitRm301);

        message.setUnit(unitRm301);
        messageDao.save(message);
    }

    @Test
    public void testRead() throws Exception {
        Job job = new Job();
        job.setCreator(adminUser);
        job.setGroupEnum(GroupEnum.ALL_PATIENTS);
        job.setSpecialty(specialty);
        job.setCreated(new Date());
        job.setMessage(message);
        job.setStatus(SendEmailEnum.PENDING);
        jobManager.save(job);

        List<Job> jobs = jobManager.getJobList(SendEmailEnum.PENDING);
        assertEquals("Wrong number of job list", 1, jobs.size());

        createEmailQueueReader.refresh(jobs);

        List<Object> checkEmailQueues = createEmailQueueReader.getList();

        assertEquals("Wrong number of EmailQueue list", 2, checkEmailQueues.size());

        assertTrue("Job not stored", ((EmailQueue)checkEmailQueues.get(0)).getJob().getId() == job.getId());
        assertTrue("Job not stored", ((EmailQueue)checkEmailQueues.get(1)).getJob().getId() == job.getId());
        assertTrue("Message not stored", ((EmailQueue)checkEmailQueues.get(0)).getMessage().getId() == message.getId());
        assertTrue("Message not stored", ((EmailQueue)checkEmailQueues.get(1)).getMessage().getId() == message.getId());
        assertTrue("User 1 not stored", ((EmailQueue)checkEmailQueues.get(0)).getRecipient().getId() == user1.getId());
        assertTrue("User 2 not stored", ((EmailQueue)checkEmailQueues.get(1)).getRecipient().getId() == user2.getId());
    }

}
