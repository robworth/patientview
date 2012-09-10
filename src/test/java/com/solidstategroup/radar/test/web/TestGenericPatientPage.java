package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.model.generic.AddPatientModel;
import com.solidstategroup.radar.model.generic.DiseaseGroup;
import com.solidstategroup.radar.model.generic.IdType;
import com.solidstategroup.radar.web.pages.patient.GenericPatientPage;
import org.junit.Test;

public class TestGenericPatientPage extends BasePageTest {

    @Test
    public void test() throws Exception {
        // Render forgotten password page
        AddPatientModel addPatientModel = new AddPatientModel();
        addPatientModel.setDiseaseGroup(new DiseaseGroup());
        addPatientModel.setPatientId("1" );
        addPatientModel.setIdType(IdType.NHS);
        tester.startPage(new GenericPatientPage(addPatientModel));

        // Assert rendered
        tester.assertRenderedPage(GenericPatientPage.class);
    }
}
