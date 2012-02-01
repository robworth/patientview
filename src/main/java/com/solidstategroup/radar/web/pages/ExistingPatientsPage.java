package com.solidstategroup.radar.web.pages;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.dataproviders.DemographicsDataProvider;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;

@AuthorizeInstantiation(User.ROLE_PROFESSIONAL)
public class ExistingPatientsPage extends BasePage {

    @SpringBean
    private DemographicsDao demographicsDao;

    public ExistingPatientsPage() {
        // Current page see src/legacy/ASP/view.aspx.vb
        // Does a branch on user ID being certain things, this implementation works on the else branch

        // e.g.
        // SELECT tbl_Demographics.RADAR_NO, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB,
        // tbl_Demographics.NHS_NO, tbl_Demographics.HOSP_NO, tbl_Demographics.DATE_REG, tbl_Centres.cAbbrev,
        // tbl_Status.sAbbrev, tbl_Diagnosis.DIAG
        // FROM tbl_Demographics
        // INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO
        // LEFT OUTER JOIN tbl_Status ON tbl_Demographics.STATUS = tbl_Status.sID
        // LEFT OUTER JOIN tbl_Centres ON tbl_Demographics.RENAL_UNIT = tbl_Centres.cID
        // WHERE (RENAL_UNIT = '" & Session("unitID") & "') ORDER BY DATE_REG DESC

        // Todo: Get centre from the logged in professional user
        Centre centre = null;
        DemographicsDataProvider demographicsDataProvider = new DemographicsDataProvider(demographicsDao, centre);

        // List existing patients
        add(new DataView<Demographics>("patients", demographicsDataProvider) {
            @Override
            protected void populateItem(Item<Demographics> item) {
                // Populate fields
                item.add(new BookmarkablePageLink<PatientPage>("edit", PatientPage.class, PatientPage.getParameters(item.getModelObject())));
                item.add(new Label("surname"), new Label("forename"));
                item.add(DateLabel.forDatePattern("dateOfBirth", RadarApplication.DATE_PATTERN));
                item.add(new Label("id"));

                // Todo: Complete
                item.add(new Label("diagnosis", "TODO"));

                item.add(new Label("nhsNumber"));
                item.add(new Label("hospitalNumber"));
                item.add(DateLabel.forDatePattern("dateRegistered", RadarApplication.DATE_PATTERN));
                item.add(new Label("status.abbreviation"));
            }
        });
    }
}
