package com.worthsoln.test.repository.ibd;

import com.worthsoln.ibd.model.Procedure;
import com.worthsoln.repository.ibd.ProcedureDao;
import com.worthsoln.test.repository.BaseDaoTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.Calendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ProcedureDaoTest extends BaseDaoTest {

    @Inject
    private ProcedureDao procedureDao;

    @Test
    public void testAddGetProcedure() throws Exception {
        Procedure procedure = getTestObject();

        /**
         * add
         */
        procedureDao.save(procedure);
        assertTrue("Can't save procedure", procedure.getId() > 0);

        /**
         * get
         */
        Procedure checkProcedure = procedureDao.get(procedure.getId());
        assertNotNull(checkProcedure);

        /**
         * delete
         */
        procedureDao.delete(procedure.getNhsno(), procedure.getUnitcode());

        Procedure deletedProcedure = procedureDao.getProcedure(procedure.getNhsno());
        assertNull("Can't delete procedure", deletedProcedure);
    }

    @Test
    public void testDeleteProcedure() throws Exception {
        Procedure procedure = getTestObject();

        /**
         * add
         */
        procedureDao.save(procedure);
        assertTrue("Can't save procedure", procedure.getId() > 0);

        /**
         * delete
         */
        procedureDao.delete(procedure.getNhsno(), procedure.getUnitcode());

        Procedure deletedProcedure = procedureDao.getProcedure(procedure.getNhsno());
        assertNull("Can't delete procedure", deletedProcedure);
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
