package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.News;
import com.worthsoln.repository.NewsDao;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class NewsDaoTest extends BaseDaoTest {

    @Inject
    private NewsDao newsDao;

    @Before
    public void createNews() {

        News news = new News();
        news.setAdmin(false);
        news.setBody("body1");
        news.setDatestamp(Calendar.getInstance());
        news.setEveryone(true);
        news.setHeadline("headline1");
        news.setPatient(false);
        news.setUnitcode("UNITCODE1");
        newsDao.save(news);

        news = new News();
        news.setAdmin(false);
        news.setBody("body2");
        news.setDatestamp(Calendar.getInstance());
        news.setEveryone(false);
        news.setHeadline("headline2");
        news.setPatient(true);
        news.setUnitcode("UNITCODE1");
        newsDao.save(news);

        news = new News();
        news.setAdmin(true);
        news.setBody("body3");
        news.setDatestamp(Calendar.getInstance());
        news.setEveryone(false);
        news.setHeadline("headline3");
        news.setPatient(true);
        news.setUnitcode("UNITCODE2");
        newsDao.save(news);

        news = new News();
        news.setAdmin(true);
        news.setBody("body4");
        news.setDatestamp(Calendar.getInstance());
        news.setEveryone(true);
        news.setHeadline("headline4");
        news.setPatient(false);
        news.setUnitcode("UNITCODE2");
        newsDao.save(news);
    }

    @Test
    public void testSaveGet() {

        News news = new News();
        news.setAdmin(true);
        news.setBody("body5");
        news.setDatestamp(Calendar.getInstance());
        news.setEveryone(true);
        news.setHeadline("headline5");
        news.setPatient(true);
        news.setUnitcode("UNITCODE1");
        newsDao.save(news);

        assertTrue("Invalid id", news.getId() > 0);
    }

    @Test
    public void testGetNewsForEveryone() {

        List<News> news = newsDao.getNewsForEveryone();

        assertEquals("Incorrect news for everyone", 2, news.size());
        // most recent first
        assertEquals("Incorrect news item", "body4", news.get(0).getBody());
    }

    @Test
    public void testGetAdminNewsForUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");

        List<News> news = newsDao.getAdminNewsForUnitCodes(unitCodes);

        assertEquals("Incorrect add news", 3, news.size());
        assertEquals("Incorrect news item", "body3", news.get(1).getBody());
    }

    @Test
    public void testGetAdminEditNewsForUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");

        List<News> news = newsDao.getAdminEditNewsForUnitCodes(unitCodes);

        assertEquals("Incorrect add news", 2, news.size());
        assertEquals("Incorrect news item", "body4", news.get(0).getBody());
    }

    @Test
    public void testGetPatientNewsForUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");

        List<News> news = newsDao.getPatientNewsForUnitCodes(unitCodes);

        assertEquals("Incorrect add news", 3, news.size());
        assertEquals("Incorrect news item", "body4", news.get(0).getBody());
    }
}
