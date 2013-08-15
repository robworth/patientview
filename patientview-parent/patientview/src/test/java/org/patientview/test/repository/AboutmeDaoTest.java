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

import org.patientview.patientview.model.Aboutme;
import org.patientview.repository.AboutmeDao;
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
