package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.IbdDiagnostic;
import com.worthsoln.repository.ibd.IbdDiagnosticDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import javax.inject.Inject;
import java.util.Calendar;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IbdDiagnosticDaoTest extends BaseDaoTest {

    @Inject
    private IbdDiagnosticDao ibdDiagnosticDao;

    @Test
    @Rollback(value = false)
    public void testAddGetDiagnostic() throws Exception {
        IbdDiagnostic ibdDiagnostic = getTestObject();

        ibdDiagnosticDao.save(ibdDiagnostic);

        assertTrue("Invalid id for new ibdDiagnostic", ibdDiagnostic.getId() > 0);

        IbdDiagnostic checkIbdDiagnostic = ibdDiagnosticDao.get(ibdDiagnostic.getId());

        assertNotNull(checkIbdDiagnostic);
    }

    private IbdDiagnostic getTestObject() throws Exception {
        IbdDiagnostic ibdDiagnostic = new IbdDiagnostic();

        ibdDiagnostic.setNhsno("1234567890");
        ibdDiagnostic.setUnitcode("1");
        ibdDiagnostic.setDate(Calendar.getInstance());
        ibdDiagnostic.setDiagnostic("Test ibdDiagnostic");

        return ibdDiagnostic;
    }

}
