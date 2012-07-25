package com.worthsoln.test.repository;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.repository.LetterDao;
import com.worthsoln.repository.UserMappingDao;
import org.junit.Test;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LetterDaoTest extends BaseDaoTest {

    @Inject
    private LetterDao letterDao;

    @Inject
    private UserMappingDao userMappingDao;

    @Test
    public void testAddGetLetter() throws Exception {
        Letter letter = getTestObject();

        letterDao.save(letter);

        assertTrue("Invalid id for new letter", letter.getId() > 0);

        Letter checkLetter = letterDao.get(letter.getId());

        assertNotNull(checkLetter);
        assertEquals("Nhs no not persisted", letter.getNhsno(), letter.getNhsno());
        assertEquals("Content not persisted", letter.getContent(), letter.getContent());
        assertEquals("Date not persisted", letter.getDate(), letter.getDate());
        assertEquals("Type not persisted", letter.getType(), letter.getType());
        assertEquals("Unit code not persisted", letter.getUnitcode(), letter.getUnitcode());
    }

    @Test
    public void testGetLettersByUsername() throws Exception {
        // create some user mappings to map the letters to
        UserMapping userMapping1 = new UserMapping();
        userMapping1.setNhsno("nhsno1");
        userMapping1.setUnitcode("unicode1");
        userMapping1.setUsername("username1");

        userMappingDao.save(userMapping1);

        assertTrue("Invalid id for new usermapping 1", userMapping1.getId() > 0);

        UserMapping userMapping2 = new UserMapping();
        userMapping2.setNhsno("nhsno2");
        userMapping2.setUnitcode("unicode2");
        userMapping2.setUsername("username2");

        userMappingDao.save(userMapping2);

        assertTrue("Invalid id for new usermapping 2", userMapping2.getId() > 0);

        // date for first as want this to be older so we can check ordering
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 5);

        // create 2 letters for user 1
        Letter letter1 = getTestObject();
        letter1.setNhsno(userMapping1.getNhsno());
        letter1.setDate(calendar);
        letterDao.save(letter1);

        assertTrue("Invalid id for letter 1", letter1.getId() > 0);

        Letter letter2 = getTestObject();
        letter2.setNhsno(userMapping1.getNhsno());
        letterDao.save(letter2);

        assertTrue("Invalid id for letter 2", letter2.getId() > 0);

        // create a third letter for user 2 - we expect this not to be pulled back
        Letter letter3 = getTestObject();
        letter3.setNhsno(userMapping2.getNhsno());
        letterDao.save(letter3);

        assertTrue("Invalid id for letter 3", letter3.getId() > 0);

        List<Letter> checkLetters = letterDao.get(userMapping1.getUsername());

        assertNotNull(checkLetters);
        assertTrue("No letters found", !checkLetters.isEmpty() && checkLetters.size() > 0);
        assertTrue("To many letters found", checkLetters.size() == 2);

        assertEquals("Letter1 was not first", checkLetters.get(0).getId(), letter1.getId());
        assertEquals("Letter2 was not second", checkLetters.get(1).getId(), letter2.getId());
    }

    private Letter getTestObject() {
        Letter letter = new Letter();
        letter.setNhsno("123456789");
        letter.setContent("Test letter");
        letter.setDate(Calendar.getInstance());
        letter.setType("testtype");
        letter.setUnitcode("testunit");
        return letter;
    }
}
