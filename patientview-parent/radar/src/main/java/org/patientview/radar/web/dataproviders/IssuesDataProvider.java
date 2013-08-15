package org.patientview.radar.web.dataproviders;

import org.patientview.radar.model.Issue;
import org.patientview.radar.model.filter.IssueFilter;
import org.patientview.radar.service.IssueManager;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class IssuesDataProvider implements IDataProvider<Issue>, SortableDataProvider {

    private IssueManager issueManager;
    private IssueFilter issueFilter;

    public IssuesDataProvider(IssueManager issueManager) {
        this.issueManager = issueManager;
        issueFilter = new IssueFilter();
    }

    public Iterator<? extends Issue> iterator(int i, int i1) {
        int pageNumber = (i / i1) + 1;
        return getResults(pageNumber, i1).iterator();
    }

    public int size() {
        return getResults(-1, -1).size();
    }

    public IModel<Issue> model(Issue issue) {
        return new Model<Issue>(issue);

    }

    public void detach() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAscending(boolean ascending) {
        issueFilter.setReverse(ascending);
    }

    public boolean isAscending() {
        return issueFilter.isReverse();
    }

    public void setSortField(String sortField) {
        issueFilter.setSortField(sortField);
    }

    public String getSortField() {
        return issueFilter.getSortField();
    }

    public boolean hasSearchCriteria() {
        return issueFilter.hasSearchCriteria();
    }

    public void addSearchCriteria(String searchField, String searchText) {
        issueFilter.addSearchCriteria(searchField, searchText);
    }

    public void removeSearchCriteria(String searchField) {
        issueFilter.removeSearchCriteria(searchField);
    }

    public void clearSearchCriteria() {
        issueFilter.getSearchFields().clear();
    }

    private List<Issue> getResults(int page, int resultsPerPage) {
        return issueManager.getIssues(issueFilter, page, resultsPerPage);
    }

}
