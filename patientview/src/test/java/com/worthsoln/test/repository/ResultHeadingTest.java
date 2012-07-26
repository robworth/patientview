package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.repository.ResultHeadingDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class ResultHeadingTest extends BaseDaoTest {

    @Inject
    private ResultHeadingDao resultHeadingDao;

    @Test
    public void testSaveGet() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEADA");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        assertTrue("Invalid id", resultHeading.getId() > 0);

        ResultHeading check = resultHeadingDao.get(resultHeading.getHeadingcode());

        assertTrue("Result heading not found via headingcode", check != null && check.getHeading().equals("heading"));
    }

    @Test
    public void testSaveGetAllDelete() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEAD");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setHeading("heading2");
        resultHeading.setHeadingcode("HEAD2");
        resultHeading.setLink("http://www.google2.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover2");

        resultHeadingDao.save(resultHeading);

        List<ResultHeading> results = resultHeadingDao.getAll();

        assertEquals("Incorrect number of results", 2, results.size());

        resultHeadingDao.delete(resultHeading.getHeadingcode());

        results = resultHeadingDao.getAll();

        assertEquals("Incorrect number of results after delete", 1, results.size());
    }
}
