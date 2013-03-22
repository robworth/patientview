package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.Allergy;
import com.worthsoln.repository.ibd.AllergyDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AllergyDaoTest extends BaseDaoTest {

    @Inject
    AllergyDao allergyDao;

    @Test
    public void testAddGetAllergy() throws Exception {
        Allergy allergy = getTestObject();

        /**
         * add
         */
        allergyDao.save(allergy);
        assertTrue("Can't save allergy", allergy.getId() > 0);

        /**
         * get
         */
        Allergy savedAllergy = allergyDao.get(allergy.getId());
        assertNotNull("Can't get allergy", savedAllergy);
    }

    @Test
    public void testDeleteAllergy() throws Exception {
        Allergy allergy = getTestObject();

        /**
         * add
         */
        allergyDao.save(allergy);
        assertTrue("Can't save allergy", allergy.getId() > 0);

        /**
         * delete
         */
        allergyDao.delete(allergy.getNhsno(), allergy.getUnitcode());

        Allergy deletedAllergy = allergyDao.getAllergy(allergy.getNhsno());
        assertNull("Can't delete allergy", deletedAllergy);

    }

    private Allergy getTestObject() throws Exception {
        Allergy allergy = new Allergy();
        allergy.setNhsno("1234567890");
        allergy.setUnitcode("unit1");
        allergy.setConfidenceLevel("confidence level");
        allergy.setDescription("description");
        allergy.setInfoSource("info source");
        allergy.setReaction("reaction");
        allergy.setRecordedDate(Calendar.getInstance());

        return allergy;
    }

}
