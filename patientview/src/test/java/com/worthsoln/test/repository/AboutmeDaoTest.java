package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Aboutme;
import com.worthsoln.repository.AboutmeDao;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AboutmeDaoTest extends BaseDaoTest {

    @Inject
    private AboutmeDao aboutmeDao;

    @Test
    public void testAddGetAboutme() throws Exception {
        Aboutme aboutme = new Aboutme();
        aboutme.setAboutme("Test about me");
        aboutme.setTalkabout("Test talkabout");
        aboutme.setNhsno("123456789");

        aboutmeDao.save(aboutme);

        assertTrue("Invalid id for new about me", aboutme.getId() > 0);

        Aboutme checkAboutme = aboutmeDao.get(aboutme.getNhsno());

        assertNotNull(checkAboutme);
        assertEquals("About me not persisted", aboutme.getAboutme(), checkAboutme.getAboutme());
        assertEquals("Talk about not persisted", aboutme.getTalkabout(), checkAboutme.getTalkabout());
        assertEquals("NHS number not persisted", aboutme.getNhsno(), checkAboutme.getNhsno());
    }
}
