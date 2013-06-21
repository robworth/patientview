package org.patientview.radar.test.dao;

import org.patientview.radar.dao.ImmunosuppressionDao;
import org.patientview.radar.model.Immunosuppression;
import org.patientview.radar.model.ImmunosuppressionTreatment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ImmunosuppressionDaoTest extends BaseDaoTest {

    @Autowired
    private ImmunosuppressionDao immunosuppressionDao;

    @Test
    public void testSaveImmunoSuppressionTreatment() throws Exception {
        // save
        ImmunosuppressionTreatment immunosuppressionTreatment = new ImmunosuppressionTreatment();
        immunosuppressionTreatment.setFirstFlag(false);
        immunosuppressionDao.saveImmunosuppressionTreatment(immunosuppressionTreatment);
        assertNotNull(immunosuppressionTreatment.getId());

        // update
        ImmunosuppressionTreatment immunosuppressionTreatment_update = new ImmunosuppressionTreatment();
        immunosuppressionTreatment_update.setId(new Long(1));
        immunosuppressionDao.saveImmunosuppressionTreatment(immunosuppressionTreatment_update);
    }

    @Test
    public void testImmunosuppressionDelete() throws Exception {
        ImmunosuppressionTreatment immunosuppressionTreatment =
                immunosuppressionDao.getImmunosuppressionTreatment(new Long(1));
        assertNotNull(immunosuppressionTreatment);

        immunosuppressionDao.deleteImmunosuppressionTreatment(immunosuppressionTreatment);
        immunosuppressionTreatment = immunosuppressionDao.getImmunosuppressionTreatment(new Long(1));
        assertNull(immunosuppressionTreatment);
    }

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

    @Test
    public void testGetImmunosuppressions() throws Exception {
        List<Immunosuppression> immunosuppressions = immunosuppressionDao.getImmunosuppressions();
        assertTrue(!immunosuppressions.isEmpty());
    }
}
