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

import org.patientview.patientview.model.Letter;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.UserMapping;
import org.patientview.repository.LetterDao;
import org.patientview.repository.UserMappingDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.junit.Before;
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

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;

    @Before
    public void setupSystem() {
        specialty = repositoryHelpers.createSpecialty("Specialty1", "ten1", "A test specialty");
    }

    @Test
    public void testAddGetLetter() throws Exception {
        Letter letter = getTestObject();

        /**
         * add
         */
        letterDao.save(letter);

        assertTrue("Invalid id for new letter", letter.getId() > 0);

        /**
         * get
         */
        Letter savedLetter = letterDao.get(letter.getId());

        assertNotNull(savedLetter);
        assertEquals("Nhs no not persisted", letter.getNhsno(), letter.getNhsno());
        assertEquals("Content not persisted", letter.getContent(), letter.getContent());
        assertEquals("Date not persisted", letter.getDate(), letter.getDate());
        assertEquals("Type not persisted", letter.getType(), letter.getType());
        assertEquals("Unit code not persisted", letter.getUnitcode(), letter.getUnitcode());
    }

    @Test
    public void testDeleteLetterByParameters() throws Exception {
        Letter letter = getTestObject();

        /**
         * add
         */
        letterDao.save(letter);

        assertTrue("Invalid id for new letter", letter.getId() > 0);

        /**
         * delete
         */
        letterDao.delete(letter.getNhsno(), letter.getUnitcode());

        List<Letter> deletedLetters = letterDao.getAll();

        assertTrue("Can't delete letter", deletedLetters.size() == 0);
    }

    @Test
    public void testDeleteLetterById() throws Exception {
        Letter letter = getTestObject();

        /**
         * add
         */
        letterDao.save(letter);

        assertTrue("Invalid id for new letter", letter.getId() > 0);

        /**
         * delete
         */
        letterDao.delete(letter.getId());

        List<Letter> deletedLetters = letterDao.getAll();

        assertTrue("Can't delete letter", deletedLetters.size() == 0);
    }


    @Test
    public void testGetLettersByUsername() throws Exception {
        // create some user mappings to map the letters to
        UserMapping userMapping1 = new UserMapping();
        userMapping1.setSpecialty(specialty);
        userMapping1.setNhsno("nhsno1");
        userMapping1.setUnitcode("unicode1");
        userMapping1.setUsername("username1");

        userMappingDao.save(userMapping1);

        assertTrue("Invalid id for new usermapping 1", userMapping1.getId() > 0);

        UserMapping userMapping2 = new UserMapping();
        userMapping2.setSpecialty(specialty);
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

        List<Letter> checkLetters = letterDao.get(userMapping1.getUsername(), specialty);

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
