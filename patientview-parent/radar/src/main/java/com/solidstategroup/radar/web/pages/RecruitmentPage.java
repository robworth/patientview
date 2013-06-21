package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.service.DemographicsManager;
import com.solidstategroup.radar.service.DiagnosisManager;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.dataproviders.DemographicsDataProvider;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;

@AuthorizeInstantiation({User.ROLE_PROFESSIONAL, User.ROLE_SUPER_USER})
public class RecruitmentPage extends BasePage{

    @SpringBean
    private DemographicsManager demographicsManager;

    @SpringBean
    private DiagnosisManager diagnosisManager;

    public RecruitmentPage() {
        DataView<Demographics> demographicsDataView = new DataView<Demographics>("recruitmenItem",
                new DemographicsDataProvider(demographicsManager)) {
            @Override
            protected void populateItem(Item<Demographics> item) {
                Demographics demographics = item.getModelObject();
                item.add(new Label("renalUnit.name"));
                item.add(new Label("id"));
                item.add(new Label("diagnosis", diagnosisManager.getDiagnosisName(demographics)));
                item.add(DateLabel.forDatePattern("dateRegistered", RadarApplication.DATE_PATTERN2));
                item.add(new Label("status.abbreviation"));
            }
        };

        add(demographicsDataView);
    }
}
