package com.solidstategroup.radar.web.pages;


import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.dataproviders.DemographicsDataProvider;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.yui.calendar.DateField;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class RecruitmentPage extends BasePage{
    @SpringBean
    private DemographicsDao demographicsDao;
    @SpringBean
    private DiagnosisDao diagnosisDao;

    public RecruitmentPage() {

        DataView<Demographics> demographicsDataView = new DataView<Demographics>("recruitmenItem", new DemographicsDataProvider(demographicsDao)) {
            @Override
            protected void populateItem(Item<Demographics> item) {
                Demographics demographics = item.getModelObject();
                item.add(new Label("renalUnit.name"));
                item.add(new Label("id"));
                Diagnosis diagnosis = diagnosisDao.getDiagnosisByRadarNumber(demographics.getId());
                if(diagnosis.getDiagnosisCode() != null) {
                    item.add(new Label("diagnosis", diagnosis.getDiagnosisCode().getAbbreviation()));
                } else {
                   item.add(new Label("diagnosis", ""));
                }
                item.add(DateLabel.forDatePattern("dateRegistered", RadarApplication.DATE_PATTERN));
                item.add(new Label("status.abbreviation"));
            }
        };

        add(demographicsDataView);
    }


}
