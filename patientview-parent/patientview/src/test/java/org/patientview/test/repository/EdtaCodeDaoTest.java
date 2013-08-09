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

import org.patientview.patientview.model.EdtaCode;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.EdtaCodeDao;
import org.patientview.test.helpers.RepositoryHelpers;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class EdtaCodeDaoTest extends BaseDaoTest {

    @Inject
    private EdtaCodeDao edtaCodeDao;

    @Inject
    private RepositoryHelpers repositoryHelpers;

    private Specialty specialty;

    @Before
    public void setupSystem() {
        specialty = repositoryHelpers.createSpecialty("Specialty1", "ten1", "A test specialty");
    }

    @Test
    public void testAddGetEdtaCode() throws Exception {
        EdtaCode edtaCode = getTestObject("1", "linkType1");

        edtaCodeDao.save(edtaCode);

        assertTrue("Invalid id for new edtacode", edtaCode.getId() > 0);

        EdtaCode checkEdtaCode = edtaCodeDao.getEdtaCode(edtaCode.getEdtaCode(), specialty);

        assertNotNull(checkEdtaCode);
        assertEquals("Edta code not persisted", edtaCode.getEdtaCode(), checkEdtaCode.getEdtaCode());
        assertEquals("Specialty not persisted", specialty, checkEdtaCode.getSpecialty());
        assertEquals("Link type not persisted", edtaCode.getLinkType(), checkEdtaCode.getLinkType());
        assertEquals("Description not persisted", edtaCode.getDescription(), checkEdtaCode.getDescription());
        assertEquals("Medical Link 01 not persisted", edtaCode.getMedicalLink01(), checkEdtaCode.getMedicalLink01());
        assertEquals("Medical Link 02 not persisted", edtaCode.getMedicalLink02(), checkEdtaCode.getMedicalLink02());
        assertEquals("Medical Link 03 not persisted", edtaCode.getMedicalLink03(), checkEdtaCode.getMedicalLink03());
        assertEquals("Medical Link 04 not persisted", edtaCode.getMedicalLink04(), checkEdtaCode.getMedicalLink04());
        assertEquals("Medical Link 05 not persisted", edtaCode.getMedicalLink05(), checkEdtaCode.getMedicalLink05());
        assertEquals("Medical Link 06 not persisted", edtaCode.getMedicalLink06(), checkEdtaCode.getMedicalLink06());
        assertEquals("Medical Link text 01 not persisted", edtaCode.getMedicalLinkText01(),
                checkEdtaCode.getMedicalLinkText01());
        assertEquals("Medical Link text 02 not persisted", edtaCode.getMedicalLinkText02(),
                checkEdtaCode.getMedicalLinkText02());
        assertEquals("Medical Link text 03 not persisted", edtaCode.getMedicalLinkText03(),
                checkEdtaCode.getMedicalLinkText03());
        assertEquals("Medical Link text 04 not persisted", edtaCode.getMedicalLinkText04(),
                checkEdtaCode.getMedicalLinkText04());
        assertEquals("Medical Link text 05 not persisted", edtaCode.getMedicalLinkText05(),
                checkEdtaCode.getMedicalLinkText05());
        assertEquals("Medical Link text 06 not persisted", edtaCode.getMedicalLinkText06(),
                checkEdtaCode.getMedicalLinkText06());
        assertEquals("Patient Link 01 not persisted", edtaCode.getPatientLink01(), checkEdtaCode.getPatientLink01());
        assertEquals("Patient Link 02 not persisted", edtaCode.getPatientLink02(), checkEdtaCode.getPatientLink02());
        assertEquals("Patient Link 03 not persisted", edtaCode.getPatientLink03(), checkEdtaCode.getPatientLink03());
        assertEquals("Patient Link 04 not persisted", edtaCode.getPatientLink04(), checkEdtaCode.getPatientLink04());
        assertEquals("Patient Link 05 not persisted", edtaCode.getPatientLink05(), checkEdtaCode.getPatientLink05());
        assertEquals("Patient Link 06 not persisted", edtaCode.getPatientLink06(), checkEdtaCode.getPatientLink06());
        assertEquals("Patient Link Text 01 not persisted", edtaCode.getPatientLinkText01(),
                checkEdtaCode.getPatientLinkText01());
        assertEquals("Patient Link Text 02 not persisted", edtaCode.getPatientLinkText02(),
                checkEdtaCode.getPatientLinkText02());
        assertEquals("Patient Link Text 03 not persisted", edtaCode.getPatientLinkText03(),
                checkEdtaCode.getPatientLinkText03());
        assertEquals("Patient Link Text 04 not persisted", edtaCode.getPatientLinkText04(),
                checkEdtaCode.getPatientLinkText04());
        assertEquals("Patient Link Text 05 not persisted", edtaCode.getPatientLinkText05(),
                checkEdtaCode.getPatientLinkText05());
        assertEquals("Patient Link Text 06 not persisted", edtaCode.getPatientLinkText06(),
                checkEdtaCode.getPatientLinkText06());
    }

    @Test
    public void testDeleteEdtaCode() throws Exception {
        EdtaCode edtaCode = getTestObject("1", "linkType1");
        String code = edtaCode.getEdtaCode();

        edtaCodeDao.save(edtaCode);

        assertTrue("Edtacode did not save", edtaCode.getId() > 0);

        edtaCodeDao.delete(code, specialty);

        EdtaCode checkEdtaCode = edtaCodeDao.getEdtaCode(code, specialty);

        assertNull(checkEdtaCode);
    }

    @Test
    public void testGetEdtaCodesByLinkType() throws Exception {
        String linkType1 = "linkType1";
        String linkType2 = "linkType2";

        EdtaCode edtaCode1 = getTestObject("1", linkType1);
        edtaCodeDao.save(edtaCode1);

        assertTrue("Edtacode1 did not save", edtaCode1.getId() > 0);

        EdtaCode edtaCode2 = getTestObject("2", linkType1);
        edtaCodeDao.save(edtaCode2);

        assertTrue("Edtacode2 did not save", edtaCode2.getId() > 0);

        // create one with another link type to make sure it doesnt come back
        EdtaCode edtaCode3 = getTestObject("3", linkType2);
        edtaCodeDao.save(edtaCode3);

        assertTrue("Edtacode3 did not save", edtaCode3.getId() > 0);

        // now pull them back for linktype1 we should get 2
        List<EdtaCode> checkEdtaCodes = edtaCodeDao.get(linkType1, specialty);

        assertNotNull(checkEdtaCodes);
        assertTrue("No edta codes found", !checkEdtaCodes.isEmpty() && checkEdtaCodes.size() > 0);
        assertTrue("To many codes found", checkEdtaCodes.size() == 2);

        // they should come out in order of edtacode so 1 should be first then 2
        assertEquals("EdtaCode1 was not first", checkEdtaCodes.get(0).getId(), edtaCode1.getId());
        assertEquals("EdtaCode2 was not second", checkEdtaCodes.get(1).getId(), edtaCode2.getId());
    }

    private EdtaCode getTestObject(String no, String linkType) {
        EdtaCode edtaCode = new EdtaCode();
        edtaCode.setEdtaCode("Test edtaCode" + no);
        edtaCode.setSpecialty(specialty);
        edtaCode.setLinkType(linkType);
        edtaCode.setDescription("Test description");
        edtaCode.setMedicalLink01("Test medicalLink01");
        edtaCode.setMedicalLink02("Test medicalLink02");
        edtaCode.setMedicalLink03("Test medicalLink03");
        edtaCode.setMedicalLink04("Test medicalLink04");
        edtaCode.setMedicalLink05("Test medicalLink05");
        edtaCode.setMedicalLink06("Test medicalLink06");
        edtaCode.setMedicalLinkText01("Test medicalLinkText01");
        edtaCode.setMedicalLinkText02("Test medicalLinkText02");
        edtaCode.setMedicalLinkText03("Test medicalLinkText03");
        edtaCode.setMedicalLinkText04("Test medicalLinkText04");
        edtaCode.setMedicalLinkText05("Test medicalLinkText05");
        edtaCode.setMedicalLinkText06("Test medicalLinkText06");
        edtaCode.setPatientLink01("Test patientLink01");
        edtaCode.setPatientLink02("Test patientLink02");
        edtaCode.setPatientLink03("Test patientLink03");
        edtaCode.setPatientLink04("Test patientLink04");
        edtaCode.setPatientLink05("Test patientLink05");
        edtaCode.setPatientLink06("Test patientLink06");
        edtaCode.setPatientLinkText01("Test patientLinkText01");
        edtaCode.setPatientLinkText02("Test patientLinkText02");
        edtaCode.setPatientLinkText03("Test patientLinkText03");
        edtaCode.setPatientLinkText04("Test patientLinkText04");
        edtaCode.setPatientLinkText05("Test patientLinkText05");
        edtaCode.setPatientLinkText06("Test patientLinkText06");

        return edtaCode;
    }
}
