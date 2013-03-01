package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.Allergy;
import com.worthsoln.repository.ibd.AllergyDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AllergyDaoTest extends BaseDaoTest {

    @Inject
    AllergyDao allergyDao;

    @Test
    public void testAddGetAllergy() throws Exception {
        Allergy allergy = getTestObject();

        allergyDao.save(allergy);

        assertTrue("Invalid id for new procedure", allergy.getId() > 0);

        Allergy checkProcedure = allergyDao.get(allergy.getId());

        assertNotNull(checkProcedure);
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
