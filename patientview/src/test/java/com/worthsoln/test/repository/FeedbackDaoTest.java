package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Feedback;
import com.worthsoln.repository.FeedbackDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FeedbackDaoTest extends BaseDaoTest {

    @Inject
    private FeedbackDao feedbackDao;

    @Test
    public void testAddGetFeedback() throws Exception {
        Feedback feedback = getTestObject();

        feedbackDao.save(feedback);

        assertTrue("Invalid id for new feedback", feedback.getId() > 0);

        Feedback checkFeedback = feedbackDao.get(feedback.getId());

        assertNotNull(checkFeedback);
        assertEquals("Username not persisted", feedback.getUsername(), checkFeedback.getUsername());
        assertEquals("Name not persisted", feedback.getName(), checkFeedback.getName());
        assertEquals("Nhs number not persisted", feedback.getNhsno(), checkFeedback.getNhsno());
        assertEquals("Unitcode not persisted", feedback.getUnitcode(), checkFeedback.getUnitcode());
        assertEquals("Date stamp not persisted", feedback.getDatestamp(), checkFeedback.getDatestamp());
        assertEquals("Comment not persisted", feedback.getComment(), checkFeedback.getComment());
        assertEquals("Comment edited not persisted", feedback.getCommentedited(), checkFeedback.getCommentedited());
        assertEquals("Anoynymous not persisted", feedback.isAnonymous(), checkFeedback.isAnonymous());
        assertEquals("Make public not persisted", feedback.isMakepublic(), checkFeedback.isMakepublic());
    }

    @Test
    public void testGetFeedbackByUnitCode() throws Exception {
        String unitcode1 = "testunitcode1";
        String unitcode2 = "testunitcode2";

        // date for first as want this to be older so we can check ordering
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 5);

        Feedback feedback1 = getTestObject();
        feedback1.setUnitcode(unitcode1);
        feedback1.setDatestamp(calendar);
        feedbackDao.save(feedback1);
        assertTrue("Invalid id for feedback 1", feedback1.getId() > 0);

        Feedback feedback2 = getTestObject();
        feedback2.setUnitcode(unitcode1);
        feedbackDao.save(feedback2);
        assertTrue("Invalid id for feedback 2", feedback2.getId() > 0);

        // make a third with different unit code and make sure it doesnt come back
        Feedback feedback3 = getTestObject();
        feedback3.setUnitcode(unitcode2);
        feedbackDao.save(feedback3);
        assertTrue("Invalid id for feedback 3", feedback3.getId() > 0);

        List<Feedback> checkFeedbacks = feedbackDao.get(unitcode1);

        assertNotNull(checkFeedbacks);
        assertTrue("No feedback found", !checkFeedbacks.isEmpty() && checkFeedbacks.size() > 0);
        assertTrue("To many feedbacks found", checkFeedbacks.size() == 2);

        // they should come out in order of datestamp desc so 1 should come out first followed by 2
        assertEquals("Feedback1 was not first", checkFeedbacks.get(0).getId(), feedback1.getId());
        assertEquals("Feedback2 was not second", checkFeedbacks.get(1).getId(), feedback2.getId());
    }

    private Feedback getTestObject() {
        Feedback feedback = new Feedback();
        feedback.setUsername("testUsername");
        feedback.setName("Test name");
        feedback.setNhsno("123456789");
        feedback.setUnitcode("TESTCODE");
        feedback.setDatestamp(Calendar.getInstance());
        feedback.setComment("Test comment");
        feedback.setCommentedited("Test commented edited");
        feedback.setAnonymous(true);
        feedback.setMakepublic(true);
        return feedback;
    }
}
