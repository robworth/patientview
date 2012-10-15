package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.Procedure;
import com.worthsoln.repository.ibd.ProcedureDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProcedureDaoTest extends BaseDaoTest {

    @Inject
    private ProcedureDao procedureDao;

    @Test
    public void testAddGetProcedure() throws Exception {
        Procedure procedure = getTestObject();

        procedureDao.save(procedure);

        assertTrue("Invalid id for new procedure", procedure.getId() > 0);

        Procedure checkProcedure = procedureDao.get(procedure.getId());

        assertNotNull(checkProcedure);
    }

    private Procedure getTestObject() throws Exception {
        Procedure procedure = new Procedure();

        procedure.setNhsno("1234567890");
        procedure.setUnitcode("1");
        procedure.setDate(Calendar.getInstance());
        procedure.setProcedure("Test procedure");

        return procedure;
    }

}
