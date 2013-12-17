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

package org.patientview.test.utils;

import org.junit.Assert;
import org.junit.Test;
import org.patientview.model.Patient;
import org.patientview.patientview.XmlImportUtils;

/**
 * User: james@solidstategroup.com
 * Date: 05/12/13
 * Time: 10:57
 */
public class XmlImportUtilsTest {

    @Test
    public void testObjectCopy(){

        Patient originalPatient = new Patient();

        Patient newPatient = new Patient();
        newPatient.setDiagnosis("This is a diagnosis test");

        XmlImportUtils.copyObject(originalPatient, newPatient);

        Assert.assertTrue("The value has been copied over", originalPatient.getDiagnosis().equals(newPatient.getDiagnosis()));

    }

}
