package com.solidstategroup.radar.test.dao;

import com.solidstategroup.radar.dao.ImmunosuppressionDao;
import com.solidstategroup.radar.model.ImmunosuppressionTreatment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ImmunosuppressionDaoTest extends BaseDaoTest {

    @Autowired
    private ImmunosuppressionDao immunosuppressionDao;

    @Test
    public void testGetImmunosuppressionTreatment() {
        ImmunosuppressionTreatment immunosuppressionTreatment = immunosuppressionDao.getImmunosuppressionTreatment(14L);
        assertNotNull("Immunosuppression was null", immunosuppressionTreatment);
        assertEquals("Wrong ID", new Long(14), immunosuppressionTreatment.getId());
        assertEquals("Wrong radar number", new Long(218), immunosuppressionTreatment.getRadarNumber());
        assertNotNull("Immuno object was null", immunosuppressionTreatment.getImmunosuppression());
        assertEquals("Wrong immuno", "Cyclophosphamide",
                immunosuppressionTreatment.getImmunosuppression().getDescription());
    }

    @Test
    public void testGetImmunosuppressionTreatmentUnknown() {
        ImmunosuppressionTreatment immunosuppressionTreatment =
                immunosuppressionDao.getImmunosuppressionTreatment(1234L);
        assertNull("Immunosuppression was not null", immunosuppressionTreatment);
    }

    @Test
    public void testGetImmunosuppressionTreatmentByRadarNumber() {
        List<ImmunosuppressionTreatment> immunosuppressions =
                immunosuppressionDao.getImmunosuppressionTreatmentByRadarNumber(218L);
        assertNotNull("List was null", immunosuppressions);
        assertEquals("Wrong size", 5, immunosuppressions.size());
    }
}
