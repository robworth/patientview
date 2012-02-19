package com.solidstategroup.radar.web.pages.admin;

import com.solidstategroup.radar.service.IssueManager;
import com.solidstategroup.radar.web.dataproviders.IssuesDataProvider;
import com.solidstategroup.radar.web.RadarApplication;
import com.solidstategroup.radar.web.components.SortLink;
import com.solidstategroup.radar.web.components.ClearLink;
import com.solidstategroup.radar.web.components.SearchField;
import com.solidstategroup.radar.model.Issue;
import com.solidstategroup.radar.model.filter.IssueFilter;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.model.Model;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

public class AdminIssuesPage extends AdminsBasePage {

    @SpringBean
    private IssueManager issueManager;

    private static final int RESULTS_PER_PAGE = 10;

    public AdminIssuesPage() {
        final IssuesDataProvider issuesDataProvider = new IssuesDataProvider(issueManager);

        add(new BookmarkablePageLink<AdminIssuesPage>("addNewIssue", AdminIssuesPage.class));

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
        issuesContainer.add(new AjaxPagingNavigator("navigator", issueList));

        // add sort links to the table column headers
        for (Map.Entry<String, String> entry : getSortFields().entrySet()) {
            add(new SortLink(entry.getKey(), entry.getValue(), issuesDataProvider) {
                @Override
                public void onClicked(AjaxRequestTarget ajaxRequestTarget) {
                    issueList.setCurrentPage(0);
                    ajaxRequestTarget.add(issuesContainer);
                }
            });
        }

        // button to clear all the filter fields for each colum
        final ClearLink clearButton = new ClearLink("clearButton", issuesDataProvider, issueList, issuesContainer);
        add(clearButton);

        // add a search field to the top of each column - these will AND each search
        for (Map.Entry<String, String> entry : getFilterFields().entrySet()) {
            add(new SearchField(entry.getKey(), entry.getValue(), issuesDataProvider) {
                @Override
                public void onChanged(AjaxRequestTarget ajaxRequestTarget) {
                    issueList.setCurrentPage(0);
                    ajaxRequestTarget.add(issuesContainer);
                    ajaxRequestTarget.add(clearButton);
                }
            });
        }
    }

    /**
     * Build a row in the dataview from the object
     * @param item Item<ProfessionalUser>
     */
    private void builtDataViewRow(Item<Issue> item) {
        Issue issue = item.getModelObject();
        item.add(new BookmarkablePageLink<AdminUserPage>("edit", AdminIssuesPage.class)); // TODO
        item.add(new Label("id", issue.getId().toString()));
        item.add(new Label("type", issue.getType().getName()));
        item.add(new Label("page", issue.getPage()));
        item.add(DateLabel.forDatePattern("dateLogged", new Model<Date>(issue.getDateLogged()),
                RadarApplication.DATE_PATTERN));
        item.add(DateLabel.forDatePattern("dateResolved", new Model<Date>(issue.getDateLogged()),
                RadarApplication.DATE_PATTERN));
        item.add(new Label("description", issue.getDescription()));
        item.add(new Label("comment", issue.getComment()));
        item.add(new Label("priority", issue.getPriority().getName()));
        item.add(new Label("status", issue.getStatus().getName()));
        item.add(DateLabel.forDatePattern("updated", new Model<Date>(issue.getUpdated()),
                RadarApplication.DATE_PATTERN));
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
                put("orderByComment", IssueFilter.Field.COMMENT.getDatabaseFieldName());
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
                //put("searchType", IssueFilter.Field.TYPE.getDatabaseFieldName());
                put("searchPage", IssueFilter.Field.PAGE.getDatabaseFieldName());
                put("searchDescription", IssueFilter.Field.DESC.getDatabaseFieldName());
                put("searchComment", IssueFilter.Field.COMMENT.getDatabaseFieldName());
                //put("searchPriority", IssueFilter.Field.PRIORITY.getDatabaseFieldName());
                //put("searchStatus", IssueFilter.Field.STATUS.getDatabaseFieldName());
                // TODO: add the dateLogged, dateResolved, updated
            }
        };
    }

}
