package com.solidstategroup.radar.web.pages;


import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.dataproviders.DemographicsDataProvider;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;


public class ExistingPatientsPage extends BasePage {

    public ExistingPatientsPage() {
        // List existing patients
        add(new DataView<Demographics>("patients", new DemographicsDataProvider()) {
            @Override
            protected void populateItem(Item<Demographics> item) {
                item.add(new BookmarkablePageLink("edit", PatientPage.class));
                item.add(new Label("surname"), new Label("forename"));
                item.add(DateLabel.forDatePattern("dateOfBirth", RadarApplication.DATE_PATTERN));
                item.add(new Label("id"));
                // Todo: Complete
                item.add(new Label("diagnosis", "TODO"));
                item.add(new Label("nhsNumber"));
                item.add(new Label("hospitalNumber"));
                item.add(DateLabel.forDatePattern("dateRegistered", RadarApplication.DATE_PATTERN));
                // Todo: Complete
                item.add(new Label("status", "TODO"));
            }
        });

    }
}
