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
