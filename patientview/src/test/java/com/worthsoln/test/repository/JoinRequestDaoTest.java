package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.JoinRequest;
import com.worthsoln.repository.JoinRequestDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class JoinRequestDaoTest extends BaseDaoTest {

    @Inject
    JoinRequestDao joinRequestDao;

    @Test
    public void testAddGetJoinRequest() throws Exception {
        JoinRequest joinRequest = getTestObject();

        /**
         * add
         */
        joinRequestDao.save(joinRequest);
        assertTrue("Can't save joinRequest", joinRequest.getId() > 0);
    }

    private JoinRequest getTestObject() throws Exception {
        JoinRequest joinRequest = new JoinRequest();

        joinRequest.setFirstName("Jack");
        joinRequest.setLastName("London");
        joinRequest.setDateOfBirth(new Date());
        joinRequest.setEmail("jack@london.com");
        joinRequest.setNhsNo("9434765919");
        joinRequest.setUnitcode("SNC01");

        return joinRequest;
    }

}
