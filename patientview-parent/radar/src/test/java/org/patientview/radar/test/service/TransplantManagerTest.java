package org.patientview.radar.test.service;

import org.patientview.radar.model.Transplant;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.service.TransplantManager;
import org.patientview.radar.test.TestPvDbSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;

import static junit.framework.Assert.assertEquals;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
public class TransplantManagerTest extends TestPvDbSchema {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    private TransplantManager transplantManager;

    @Test
    public void testSaveTransplantValidatoin() throws Exception{
        // save invalid transplant and check the list of errors
        Transplant transplant = new Transplant();
        transplant.setRadarNumber(219L);
        transplant.setDate(DATE_FORMAT.parse("20/09/2009"));
        transplant.setDateRecurr(DATE_FORMAT.parse("19/09/2009"));
        Transplant.RejectData rejectData = new Transplant.RejectData();
        rejectData.setFailureDate(DATE_FORMAT.parse("19/09/2009"));
        transplant.setDateFailureRejectData(rejectData);
        try {
            transplantManager.saveTransplant(transplant);
        } catch (InvalidModelException e) {
            assertEquals(e.getErrors().size(), 4);
        }
    }
}
