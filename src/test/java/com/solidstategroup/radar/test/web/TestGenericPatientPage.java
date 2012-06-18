package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.model.generic.DiseaseGroup;
import com.solidstategroup.radar.web.pages.patient.AddPatientPage;
import com.solidstategroup.radar.web.pages.patient.GenericPatientPage;
import org.junit.Test;

public class TestGenericPatientPage extends BasePageTest {

    @Test
    public void test() throws Exception {
        // Render forgotten password page
        AddPatientPage.AddPatientModel addPatientModel = new AddPatientPage.AddPatientModel();
        addPatientModel.setDiseaseGroup(new DiseaseGroup());
        addPatientModel.setId("1" );
        addPatientModel.setIdType(AddPatientPage.AddPatientModel.IdType.NHS);
        tester.startPage(new GenericPatientPage(addPatientModel));

        // Assert rendered
        tester.assertRenderedPage(GenericPatientPage.class);
    }
}
