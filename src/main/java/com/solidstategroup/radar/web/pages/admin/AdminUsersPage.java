package com.solidstategroup.radar.web.pages.admin;

import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.util.TripleDes;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.dataproviders.ProfessionalUserDataProvider;
import com.solidstategroup.radar.service.UserManager;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdminUsersPage extends AdminsBasePage {

    @SpringBean
    private UserManager userManager;
    
    private static final int RESULTS_PER_PAGE = 10;

    public AdminUsersPage() {
        final ProfessionalUserDataProvider professionalUserDataProvider = new ProfessionalUserDataProvider(userManager);

        add(new BookmarkablePageLink<AdminUserPage>("addNewUser", AdminUserPage.class));

        final WebMarkupContainer usersContainer = new WebMarkupContainer("usersContainer");
        usersContainer.setOutputMarkupId(true);
        add(usersContainer);

        final DataView<ProfessionalUser> userList = new DataView<ProfessionalUser>("users",
                professionalUserDataProvider) {
            @Override
            protected void populateItem(Item<ProfessionalUser> item) {
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
                        RadarApplication.DATE_PATTERN));
                item.add(new Label("GMC", user.getGmc()));

                String username;
                try {
                    username = TripleDes.decrypt(user.getUsernameHash());
                } catch (Exception e) {
                    username = "";
                }

                item.add(new Label("username", username));

                String password;
                try {
                    password = TripleDes.decrypt(user.getPasswordHash());
                } catch (Exception e) {
                    password = "";
                }

                item.add(new Label("password", password));
            }
        };
        userList.setItemsPerPage(RESULTS_PER_PAGE);
        usersContainer.add(userList);

        // add paging element
        usersContainer.add(new AjaxPagingNavigator("navigator", userList));

        // add sort links to the table column headers
        for (Map.Entry<String, ProfessionalUserFilter.UserField> entry : getSortFields().entrySet()) {
            add(new SortLink(entry.getKey(), entry.getValue(), professionalUserDataProvider) {
                @Override
                public void onClicked(AjaxRequestTarget ajaxRequestTarget) {
                    userList.setCurrentPage(0);
                    ajaxRequestTarget.add(usersContainer);
                }
            });
        }

        // button to clear all the filter fields for each colum
        final AjaxLink clearButton = new AjaxLink("clearButton") {
            @Override
            protected IAjaxCallDecorator getAjaxCallDecorator() {
                return new IAjaxCallDecorator() {
                    public CharSequence decorateScript(Component component, CharSequence charSequence) {
                        return "$('input.dxeEditArea').val(''); " + charSequence;
                    }

                    public CharSequence decorateOnSuccessScript(Component component, CharSequence charSequence) {
                        return charSequence;
                    }

                    public CharSequence decorateOnFailureScript(Component component, CharSequence charSequence) {
                        return charSequence;
                    }
                };
            }

            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                if (professionalUserDataProvider.getUserFilter().hasSearchFilter()) {
                    professionalUserDataProvider.getUserFilter().getSearchFields().clear();
                    userList.setCurrentPage(0);
                    ajaxRequestTarget.add(usersContainer);
                    ajaxRequestTarget.add(this);
                }
            }

            @Override
            public boolean isVisible() {
                return professionalUserDataProvider.getUserFilter().hasSearchFilter();
            }
        };
        clearButton.setOutputMarkupId(true);
        clearButton.setOutputMarkupPlaceholderTag(true);
        add(clearButton);

        // add a search field to the top of each column - these will AND each search
        for (Map.Entry<String, ProfessionalUserFilter.UserField> entry : getFilterFields().entrySet()) {
            add(new SearchField(entry.getKey(), entry.getValue(), professionalUserDataProvider) {
                @Override
                public void onChanged(AjaxRequestTarget ajaxRequestTarget) {
                    userList.setCurrentPage(0);
                    ajaxRequestTarget.add(usersContainer);
                    ajaxRequestTarget.add(clearButton);
                }
            });
        }
    }

    /**
     * List of columns that can be used to sort the results - will return ID of el to be bound to and the field to sort
     * @return Map<String, ProfessionalUserFilter.UserField>
     */
    private Map<String, ProfessionalUserFilter.UserField> getSortFields() {
        return new HashMap<String, ProfessionalUserFilter.UserField>() {
            {
                put("orderBySurname", ProfessionalUserFilter.UserField.SURNAME);
                put("orderByForename", ProfessionalUserFilter.UserField.FORENAME);
                put("orderByTitle", ProfessionalUserFilter.UserField.TITLE);
                put("orderByRole", ProfessionalUserFilter.UserField.ROLE);
                put("orderByEmail", ProfessionalUserFilter.UserField.EMAIL);
                put("orderByCentre", ProfessionalUserFilter.UserField.CENTRE);
                put("orderByDateRegistered", ProfessionalUserFilter.UserField.REGISTRATION_DATE);
                put("orderByGMC", ProfessionalUserFilter.UserField.GMC);
            }
        };
    }

    /**
     * List of column filters - will return ID of el to be bound to and the field to filter
     * @return Map<String, ProfessionalUserFilter.UserField>
     */
    private Map<String, ProfessionalUserFilter.UserField> getFilterFields() {
        return new HashMap<String, ProfessionalUserFilter.UserField>() {
            {
                put("searchSurname", ProfessionalUserFilter.UserField.SURNAME);
                put("searchForename", ProfessionalUserFilter.UserField.FORENAME);
                put("searchTitle", ProfessionalUserFilter.UserField.TITLE);
                put("searchRole", ProfessionalUserFilter.UserField.ROLE);
                put("searchEmail", ProfessionalUserFilter.UserField.EMAIL);
                put("searchCentre", ProfessionalUserFilter.UserField.CENTRE);
                put("searchGMC", ProfessionalUserFilter.UserField.GMC);
                // TODO: add the date filter
            }
        };
    }

    /**
     * TextField bound to a Data field - this will update the filter when user puts anything int he field
     * This will only update the settings in the provider the onChanged has to be overridden to update the objects
     * on the page
     */
    private abstract class SearchField extends TextField<String> {
        private ProfessionalUserDataProvider dataProvider;
        private ProfessionalUserFilter.UserField searchField;

        private SearchField(final String id, final ProfessionalUserFilter.UserField searchField,
                            final ProfessionalUserDataProvider dataProvider) {
            super(id, new Model<String>(""));

            this.dataProvider = dataProvider;
            this.searchField = searchField;

            for (final String s : new String[]{"onchange", "onblur", "onkeyup"}) {
                add(new AjaxFormComponentUpdatingBehavior(s) {
                    @Override
                    protected void onUpdate(AjaxRequestTarget target) {
                        changed(target);
                    }
                });
            }
        }

        private void changed(final AjaxRequestTarget ajaxRequestTarget) {
            final String value = getModelObject();

            if (value == null || value.length() == 0) {
                // if they type nothing in then just bring back all the results
                dataProvider.getUserFilter().removeSearchCriteria(searchField);
                onChanged(ajaxRequestTarget);
            } else {
                dataProvider.getUserFilter().addSearchCriteria(searchField, value);
                onChanged(ajaxRequestTarget);
            }
        }

        public abstract void onChanged(final AjaxRequestTarget ajaxRequestTarget);
    }

    /**
     * AjaxLink bound to a Data field - this will update the filter when user clicks and set the sort
     * It will keep track of whether its currently ASC or DESC
     * This will only update the settings in the provider the onChanged has to be overridden to update the objects
     * on the page
     */
    private abstract class SortLink extends AjaxLink {
        private SortOrder order = SortOrder.ASCENDING;
        private ProfessionalUserFilter.UserField sortField;
        private ProfessionalUserDataProvider dataProvider;

        private SortLink(final String id, ProfessionalUserFilter.UserField sortField, 
                         final ProfessionalUserDataProvider dataProvider) {
            super(id);
            this.sortField = sortField;
            this.dataProvider = dataProvider;
        }

        @Override
        public void onClick(final AjaxRequestTarget ajaxRequestTarget) {
            if (order.equals(SortOrder.ASCENDING)) {
                order = SortOrder.DESCENDING;
            } else {
                order = SortOrder.ASCENDING;
            }

            dataProvider.getUserFilter().setSortField(sortField);
            dataProvider.setAscending(order == SortOrder.ASCENDING);

            onClicked(ajaxRequestTarget);
        }

        public abstract void onClicked(final AjaxRequestTarget ajaxRequestTarget);
    }
}
