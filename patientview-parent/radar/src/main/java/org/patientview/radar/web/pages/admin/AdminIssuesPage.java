package org.patientview.radar.web.pages.admin;

import org.patientview.radar.model.Issue;
import org.patientview.radar.model.enums.IssuePriority;
import org.patientview.radar.model.enums.IssueStatus;
import org.patientview.radar.model.enums.IssueType;
import org.patientview.radar.model.filter.IssueFilter;
import org.patientview.radar.service.IssueManager;
import org.patientview.radar.web.components.ClearLink;
import org.patientview.radar.web.components.SearchDateField;
import org.patientview.radar.web.components.SearchDropDownChoice;
import org.patientview.radar.web.components.SearchField;
import org.patientview.radar.web.components.SortLink;
import org.patientview.radar.web.dataproviders.IssuesDataProvider;
import org.patientview.radar.web.panels.RadarAjaxPagingNavigator;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AdminIssuesPage extends AdminsBasePage {

    @SpringBean
    private IssueManager issueManager;

    private static final int RESULTS_PER_PAGE = 10;

    public AdminIssuesPage() {
        final IssuesDataProvider issuesDataProvider = new IssuesDataProvider(issueManager);

        add(new BookmarkablePageLink<AdminIssuesPage>("addNewIssue", AdminIssuePage.class));

        final WebMarkupContainer issuesContainer = new WebMarkupContainer("issuesContainer");
        issuesContainer.setOutputMarkupId(true);
        add(issuesContainer);

        final DataView<Issue> issueList = new DataView<Issue>("issues",
                issuesDataProvider) {
            @Override
            protected void populateItem(Item<Issue> item) {
                builtDataViewRow(item);
            }
        };
        issueList.setItemsPerPage(RESULTS_PER_PAGE);
        issuesContainer.add(issueList);

        // add paging element
        issuesContainer.add(new RadarAjaxPagingNavigator("navigator", issueList, issuesDataProvider.size()));

        // button to clear all the filter fields for each colum
        final ClearLink clearButton = new ClearLink("clearButton", issuesDataProvider, issueList, issuesContainer);
        add(clearButton);

        // add sort links to the table column headers
        for (Map.Entry<String, String> entry : getSortFields().entrySet()) {
            add(new SortLink(entry.getKey(), entry.getValue(), issuesDataProvider, issueList, 
                    Arrays.asList(issuesContainer)));
        }

        // add a search field to the top of each column - these will AND each search
        for (Map.Entry<String, String> entry : getFilterFields().entrySet()) {
            add(new SearchField(entry.getKey(), entry.getValue(), issuesDataProvider, issueList,
                    Arrays.asList(issuesContainer, clearButton)));
        }

        // some of the sort fields are drop downs so add these
        add(new SearchDropDownChoice<IssueType>("searchType", Arrays.asList(IssueType.values()),
                IssueFilter.Field.TYPE.getDatabaseFieldName(), issuesDataProvider, issueList,
                Arrays.asList(issuesContainer, clearButton)));


        add(new SearchDropDownChoice<IssuePriority>("searchPriority", Arrays.asList(IssuePriority.values()),
                IssueFilter.Field.PRIORITY.getDatabaseFieldName(), issuesDataProvider, issueList,
                Arrays.asList(issuesContainer, clearButton)));

        add(new SearchDropDownChoice<IssueStatus>("searchStatus", Arrays.asList(IssueStatus.values()),
                IssueFilter.Field.STATUS.getDatabaseFieldName(), issuesDataProvider, issueList,
                Arrays.asList(issuesContainer, clearButton)));

        add(new SearchDateField("searchDateLogged", IssueFilter.Field.DATE_LOGGED.getDatabaseFieldName(),
                issuesDataProvider, issueList, Arrays.asList(issuesContainer, clearButton)));

        add(new SearchDateField("searchDateResolved", IssueFilter.Field.DATE_RESOLVED.getDatabaseFieldName(),
                issuesDataProvider, issueList, Arrays.asList(issuesContainer, clearButton)));

        add(new SearchDateField("searchUpdated", IssueFilter.Field.UPDATED.getDatabaseFieldName(),
                issuesDataProvider, issueList, Arrays.asList(issuesContainer, clearButton)));
    }

    /**
     * Build a row in the dataview from the object
     * @param item Item<ProfessionalUser>
     */
    private void builtDataViewRow(Item<Issue> item) {
        Issue issue = item.getModelObject();
        item.add(new BookmarkablePageLink<AdminIssuePage>("edit", AdminIssuePage.class,
                AdminIssuePage.getPageParameters(issue)));
        item.add(new Label("id", issue.getId().toString()));
        item.add(new Label("type", issue.getType().getName()));
        item.add(new Label("page", issue.getPage()));
        item.add(DateLabel.forDatePattern("dateLogged", new Model<Date>(issue.getDateLogged()),
                SearchDateField.DATABASE_DATE_PATTERN));
        item.add(DateLabel.forDatePattern("dateResolved", new Model<Date>(issue.getDateLogged()),
                SearchDateField.DATABASE_DATE_PATTERN));
        item.add(new Label("description", issue.getDescription()));
        item.add(new Label("comment", issue.getComments()));
        item.add(new Label("priority", issue.getPriority().getName()));
        item.add(new Label("status", issue.getStatus().getName()));
        item.add(DateLabel.forDatePattern("updated", new Model<Date>(issue.getUpdated()),
                SearchDateField.DATABASE_DATE_PATTERN));
    }

    /**
     * List of columns that can be used to sort the results - will return ID of el to be bound to and the field to sort
     * @return Map<String, IssueFilter.UserField>
     */
    private Map<String, String> getSortFields() {
        return new HashMap<String, String>() {
            {
                put("orderById", IssueFilter.Field.ID.getDatabaseFieldName());
                put("orderByType", IssueFilter.Field.TYPE.getDatabaseFieldName());
                put("orderByPage", IssueFilter.Field.PAGE.getDatabaseFieldName());
                put("orderByDateLogged", IssueFilter.Field.DATE_LOGGED.getDatabaseFieldName());
                put("orderByDateResolved", IssueFilter.Field.DATE_RESOLVED.getDatabaseFieldName());
                put("orderByDescription", IssueFilter.Field.DESC.getDatabaseFieldName());
                put("orderByComment", IssueFilter.Field.COMMENTS.getDatabaseFieldName());
                put("orderByPriority", IssueFilter.Field.PRIORITY.getDatabaseFieldName());
                put("orderByStatus", IssueFilter.Field.STATUS.getDatabaseFieldName());
                put("orderByUpdated", IssueFilter.Field.UPDATED.getDatabaseFieldName());
            }
        };
    }

    /**
     * List of column filters - will return ID of el to be bound to and the field to filter
     * @return Map<String, IssueFilter.UserField>
     */
    private Map<String, String> getFilterFields() {
        return new HashMap<String, String>() {
            {
                put("searchId", IssueFilter.Field.ID.getDatabaseFieldName());
                put("searchPage", IssueFilter.Field.PAGE.getDatabaseFieldName());
                put("searchDescription", IssueFilter.Field.DESC.getDatabaseFieldName());
                put("searchComment", IssueFilter.Field.COMMENTS.getDatabaseFieldName());
            }
        };
    }

}
