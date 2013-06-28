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

package org.patientview.test.repository;

import org.patientview.patientview.model.News;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.NewsDao;
import org.patientview.test.helpers.RepositoryHelpers;
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

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;

    @Before
    public void createNews() {

        specialty = repositoryHelpers.createSpecialty("Specialty1", "specialty1", "A test specialty");

        News news = new News();
        news.setSpecialty(specialty);
        news.setAdmin(false);
        news.setBody("body1");
        news.setDatestamp(Calendar.getInstance());
        news.setEveryone(true);
        news.setHeadline("headline1");
        news.setPatient(false);
        news.setUnitcode("UNITCODE1");
        newsDao.save(news);

        news = new News();
        news.setSpecialty(specialty);
        news.setAdmin(false);
        news.setBody("body2");
        news.setDatestamp(Calendar.getInstance());
        news.setEveryone(false);
        news.setHeadline("headline2");
        news.setPatient(true);
        news.setUnitcode("UNITCODE1");
        newsDao.save(news);

        news = new News();
        news.setSpecialty(specialty);
        news.setAdmin(true);
        news.setBody("body3");
        news.setDatestamp(Calendar.getInstance());
        news.setEveryone(false);
        news.setHeadline("headline3");
        news.setPatient(true);
        news.setUnitcode("UNITCODE2");
        newsDao.save(news);

        news = new News();
        news.setSpecialty(specialty);
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
        news.setSpecialty(specialty);
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

        List<News> news = newsDao.getNewsForEveryone(specialty);

        assertEquals("Incorrect news for everyone", 2, news.size());

        // note cannot check order as they may have the same datatime stamp and the order cannot be guaranteed
    }

    @Test
    public void testGetAdminNewsForUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");

        List<News> news = newsDao.getAdminNewsForUnitCodes(unitCodes, specialty);

        assertEquals("Incorrect add news", 3, news.size());
        // note cannot check order as they may have the same datatime stamp and the order cannot be guaranteed
    }

    @Test
    public void testGetAdminEditNewsForUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");

        List<News> news = newsDao.getAdminEditNewsForUnitCodes(unitCodes, specialty);

        assertEquals("Incorrect add news", 2, news.size());
        // note cannot check order as they may have the same datatime stamp and the order cannot be guaranteed
    }

    @Test
    public void testGetPatientNewsForUnitCodes() {

        List<String> unitCodes = new ArrayList<String>();
        unitCodes.add("UNITCODE2");

        List<News> news = newsDao.getPatientNewsForUnitCodes(unitCodes, specialty);

        assertEquals("Incorrect add news", 3, news.size());
//        assertEquals("Incorrect news item", "body4", news.get(0).getBody());
    }
}
