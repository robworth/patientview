package org.patientview.radar.web.pages.admin;

import org.patientview.radar.model.enums.ExportType;
import org.patientview.radar.model.filter.ProfessionalUserFilter;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.service.ExportManager;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.web.components.ClearLink;
import org.patientview.radar.web.components.SearchDateField;
import org.patientview.radar.web.components.SearchField;
import org.patientview.radar.web.components.SortLink;
import org.patientview.radar.web.dataproviders.ProfessionalUserDataProvider;
import org.patientview.radar.web.panels.RadarAjaxPagingNavigator;
import org.patientview.radar.web.resources.RadarResourceFactory;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdminUsersPage extends AdminsBasePage {

    @SpringBean
    private UserManager userManager;
    @SpringBean
    private ExportManager exportManager;

    private static final int RESULTS_PER_PAGE = 10;

    public AdminUsersPage() {
        final ProfessionalUserDataProvider professionalUserDataProvider = new ProfessionalUserDataProvider(userManager);

        add(new ResourceLink("exportPdf", RadarResourceFactory.getExportResource(
                exportManager.getProfessionalUsersExportData(ExportType.PDF), "users" +
                AdminsBasePage.EXPORT_FILE_NAME_SUFFIX, ExportType.PDF)));

        add(new ResourceLink("exportExcel", RadarResourceFactory.getExportResource(
                exportManager.getProfessionalUsersExportData(ExportType.EXCEL), "users" +
                AdminsBasePage.EXPORT_FILE_NAME_SUFFIX, ExportType.EXCEL)));

        add(new BookmarkablePageLink<AdminUserPage>("addNewUser", AdminUserPage.class));

        final WebMarkupContainer usersContainer = new WebMarkupContainer("usersContainer");
        usersContainer.setOutputMarkupId(true);
        add(usersContainer);

        final DataView<ProfessionalUser> userList = new DataView<ProfessionalUser>("users",
                professionalUserDataProvider) {
            @Override
            protected void populateItem(Item<ProfessionalUser> item) {
                builtDataViewRow(item);
            }
        };
        userList.setItemsPerPage(RESULTS_PER_PAGE);
        usersContainer.add(userList);

        // add paging element
        usersContainer.add(new RadarAjaxPagingNavigator("navigator", userList, professionalUserDataProvider.size()));

        // add sort links to the table column headers
        for (Map.Entry<String, String> entry : getSortFields().entrySet()) {
            add(new SortLink(entry.getKey(), entry.getValue(), professionalUserDataProvider, userList,
                    Arrays.asList(usersContainer)));
        }

        // button to clear all the filter fields for each colum
        final ClearLink clearButton = new ClearLink("clearButton", professionalUserDataProvider, userList,
                usersContainer);
        add(clearButton);

        // add a search field to the top of each column - these will AND each search
        for (Map.Entry<String, String> entry : getFilterFields().entrySet()) {
            add(new SearchField(entry.getKey(), entry.getValue(), professionalUserDataProvider, userList,
                    Arrays.asList(usersContainer, clearButton)));
        }

        // add a date filter
        add(new SearchDateField("searchDateRegistered",
                ProfessionalUserFilter.UserField.REGISTRATION_DATE.getDatabaseFieldName(),
                professionalUserDataProvider, userList, Arrays.asList(usersContainer, clearButton)));
    }

    /**
     * Build a row in the dataview from the object
     *
     * @param item Item<ProfessionalUser>
     */
    private void builtDataViewRow(Item<ProfessionalUser> item) {
        ProfessionalUser user = item.getModelObject();
        item.add(new BookmarkablePageLink<AdminUserPage>("edit", AdminUserPage.class,
                AdminUserPage.getPageParameters(user)));
        item.add(new Label("surname", user.getSurname()));
        item.add(new Label("forename", user.getForename()));
        item.add(new Label("title", user.getTitle()));
        item.add(new Label("role", user.getRole()));
        item.add(new Label("email", user.getEmail()));
        item.add(new Label("centre", user.getCentre().getName()));
        item.add(DateLabel.forDatePattern("dateRegistered", new Model<Date>(user.getDateRegistered()),
                SearchDateField.DATABASE_DATE_PATTERN));
        item.add(new Label("GMC", user.getGmc()));

        item.add(new Label("username", user.getUsername()));
    }

    /**
     * List of columns that can be used to sort the results - will return ID of el to be bound to and the field to sort
     *
     * @return Map<String, ProfessionalUserFilter.UserField>
     */
    private Map<String, String> getSortFields() {
        return new HashMap<String, String>() {
            {
                put("orderBySurname", ProfessionalUserFilter.UserField.SURNAME.getDatabaseFieldName());
                put("orderByForename", ProfessionalUserFilter.UserField.FORENAME.getDatabaseFieldName());
                put("orderByTitle", ProfessionalUserFilter.UserField.TITLE.getDatabaseFieldName());
                put("orderByRole", ProfessionalUserFilter.UserField.ROLE.getDatabaseFieldName());
                put("orderByEmail", ProfessionalUserFilter.UserField.EMAIL.getDatabaseFieldName());
                put("orderByCentre", ProfessionalUserFilter.UserField.CENTRE.getDatabaseFieldName());
                put("orderByDateRegistered", ProfessionalUserFilter.UserField.REGISTRATION_DATE.getDatabaseFieldName());
                put("orderByGMC", ProfessionalUserFilter.UserField.GMC.getDatabaseFieldName());
            }
        };
    }

    /**
     * List of column filters - will return ID of el to be bound to and the field to filter
     *
     * @return Map<String, ProfessionalUserFilter.UserField>
     */
    private Map<String, String> getFilterFields() {
        return new HashMap<String, String>() {
            {
                put("searchSurname", ProfessionalUserFilter.UserField.SURNAME.getDatabaseFieldName());
                put("searchForename", ProfessionalUserFilter.UserField.FORENAME.getDatabaseFieldName());
                put("searchTitle", ProfessionalUserFilter.UserField.TITLE.getDatabaseFieldName());
                put("searchRole", ProfessionalUserFilter.UserField.ROLE.getDatabaseFieldName());
                put("searchEmail", ProfessionalUserFilter.UserField.EMAIL.getDatabaseFieldName());
                put("searchCentre", ProfessionalUserFilter.UserField.CENTRE.getDatabaseFieldName());
                put("searchGMC", ProfessionalUserFilter.UserField.GMC.getDatabaseFieldName());
            }
        };
    }
}
