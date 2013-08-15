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

package org.patientview.test.repository.ibd;

import org.patientview.ibd.model.Procedure;
import org.patientview.repository.ibd.ProcedureDao;
import org.patientview.test.repository.BaseDaoTest;
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
