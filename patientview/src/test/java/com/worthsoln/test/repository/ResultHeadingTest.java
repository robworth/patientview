package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.repository.ResultHeadingDao;
import com.worthsoln.test.helpers.RepositoryHelpers;
import org.junit.Before;
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

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Tenancy tenancy;

    @Before
    public void setupSystem() {
        tenancy = repositoryHelpers.createTenancy("Tenancy1", "ten1", "A test tennacy");
    }

    @Test
    public void testSaveGet() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEADA");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        assertTrue("Invalid id", resultHeading.getId() > 0);

        ResultHeading check = resultHeadingDao.get(resultHeading.getHeadingcode(), tenancy);

        assertTrue("Result heading not found via headingcode", check != null && check.getHeading().equals("heading"));
    }

    @Test
    public void testSaveGetAllDelete() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEAD");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading2");
        resultHeading.setHeadingcode("HEAD2");
        resultHeading.setLink("http://www.google2.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover2");

        resultHeadingDao.save(resultHeading);

        List<ResultHeading> results = resultHeadingDao.getAll(tenancy);

        assertEquals("Incorrect number of results", 2, results.size());

        resultHeadingDao.delete(resultHeading.getHeadingcode(), tenancy);

        results = resultHeadingDao.getAll(tenancy);

        assertEquals("Incorrect number of results after delete", 1, results.size());
    }

    @Test
    public void testSaveGetUsingPanel() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEAD");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading2");
        resultHeading.setHeadingcode("HEAD2");
        resultHeading.setLink("http://www.google2.com/");
        resultHeading.setPanel(1);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover2");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading3");
        resultHeading.setHeadingcode("HEAD3");
        resultHeading.setLink("http://www.google3.com/");
        resultHeading.setPanel(1);
        resultHeading.setPanelorder(1);
        resultHeading.setRollover("rollover3");

        resultHeadingDao.save(resultHeading);

        List<ResultHeading> results = resultHeadingDao.get(1, tenancy);

        assertEquals("Incorrect number of results", 2, results.size());

        // heading3 returned first due to panel order
        assertEquals("Incorrect result heading returned", resultHeading, results.get(0));
    }

    @Test
    public void testGetPanels() {

        ResultHeading resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading");
        resultHeading.setHeadingcode("HEAD");
        resultHeading.setLink("http://www.google.com/");
        resultHeading.setPanel(2);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading2");
        resultHeading.setHeadingcode("HEAD2");
        resultHeading.setLink("http://www.google2.com/");
        resultHeading.setPanel(1);
        resultHeading.setPanelorder(3);
        resultHeading.setRollover("rollover2");

        resultHeadingDao.save(resultHeading);

        resultHeading = new ResultHeading();
        resultHeading.setTenancy(tenancy);
        resultHeading.setHeading("heading3");
        resultHeading.setHeadingcode("HEAD3");
        resultHeading.setLink("http://www.google3.com/");
        resultHeading.setPanel(1);
        resultHeading.setPanelorder(1);
        resultHeading.setRollover("rollover3");

        resultHeadingDao.save(resultHeading);

        List<Panel> results = resultHeadingDao.getPanels(tenancy);

        assertEquals("Incorrect number of results", 2, results.size());

        // heading3 returned first due to panel order
        assertEquals("Incorrect panel returned first", 1, results.get(0).getPanel());
        assertEquals("Incorrect panel returned last", 2, results.get(1).getPanel());
    }
}
