package com.worthsoln.test.service;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.*;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.JobManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.test.helpers.SecurityHelpers;
import com.worthsoln.test.helpers.ServiceHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

/**
 *      These tests require an admin adminUser to be logged into a specialty
 */
public class JobManagerTest extends BaseServiceTest {

    @Inject
    private ServiceHelpers serviceHelpers;

    @Inject
    private JobManager jobManager;


    @Test
    public void testAddGetJob() throws Exception {
        User user1 = serviceHelpers.createUser("test 1", "tester1@test.com", "test1", "Test 1");
        User user2 = serviceHelpers.createUser("test 2", "tester2@test.com", "test2", "Test 2");
        Conversation conversation = serviceHelpers.createConversation("Test subject", user1, user2, true);

        Message message = serviceHelpers.createMessage(conversation, user1, user2, "This is a message", true);
        Specialty specialty = serviceHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        Job job1 = new Job();
        job1.setCreator(user1);
        job1.setMessage(message);
        job1.setSpecialty(specialty);
        job1.setStatus(SendEmailEnum.PENDING);
        jobManager.save(job1);

        Job job2 = new Job();
        job2.setCreator(user2);
        job2.setMessage(message);
        job2.setSpecialty(specialty);
        job2.setStatus(SendEmailEnum.FAILED);
        jobManager.save(job2);


        assertTrue("Invalid id for Job 1 ", job1.getId() > 0);
        assertTrue("Invalid id for Job 2 ", job2.getId() > 0);

        List<Job> checkJobList = jobManager.getJobList(SendEmailEnum.PENDING);

        assertNotNull(checkJobList);
        assertEquals("Wrong number of job size", checkJobList.size(), 1);
        assertFalse("Job 2 found in SendEmailEnum PENDING", checkJobList.contains(job2));
        assertTrue("Job 1 not found in SendEmailEnum PENDING", checkJobList.contains(job1));
        assertEquals("Specialty not stored", checkJobList.get(0).getSpecialty(), specialty);
        assertEquals("Message not stored", checkJobList.get(0).getMessage(), message);
        assertEquals("User not stored", checkJobList.get(0).getCreator(), user1);
    }

    @Test
    public void testGetSpecialGroupUsers() {
        Specialty specialty1 = serviceHelpers.createSpecialty("Specialty 1", "Specialty1", "Test description");
        User adminUser = serviceHelpers.createUserWithMapping("adminuser", "test@admin.com", "p", "Admin", "unitA", "nhs1", specialty1);
        User user1 = serviceHelpers.createUserWithMapping("testname1", "test1@admin.com", "p", "test1", "unitA", "nhstest1", specialty1);
        User user2 = serviceHelpers.createUserWithMapping("testname2", "test2@admin.com", "p", "test2", "unitA", "nhstest2", specialty1);
        User user3 = serviceHelpers.createUserWithMapping("testname3-GP", "test3@admin.com", "p", "test3", "unitA", "nhstest3", specialty1);
        User user4 = serviceHelpers.createUserWithMapping("testname4", "test4@admin.com", "p", "test4", "unitB", "nhstest4", specialty1);

        // Add SpecialtyUserRole
        serviceHelpers.createSpecialtyUserRole(specialty1, adminUser, "unitadmin");
        serviceHelpers.createSpecialtyUserRole(specialty1, user1, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty1, user2, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty1, user3, "patient");
        serviceHelpers.createSpecialtyUserRole(specialty1, user4, "patient");

        List<User> checkUserList = jobManager.getSpecialGroupUsers(adminUser, specialty1, "patient");

        assertEquals("Wrong number of users", checkUserList.size(), 2);
        assertFalse("User 3 found in users", checkUserList.contains(user3));
        assertFalse("User 4 found in users", checkUserList.contains(user4));
        assertTrue("User 1 not found in users", checkUserList.contains(user1));
        assertTrue("User 2 not found in users", checkUserList.contains(user2));
    }
}
