package org.patientview.radar.service.impl;

import org.patientview.radar.dao.IssueDao;
import org.patientview.radar.model.Issue;
import org.patientview.radar.model.filter.IssueFilter;
import org.patientview.radar.service.IssueManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IssueManagerImpl implements IssueManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueManagerImpl.class);

    private IssueDao issueDao;

    public Issue getIssue(Long id) {
        return issueDao.getIssue(id);
    }

    public void saveIssue(Issue issue) throws Exception {
        issueDao.saveIssue(issue);
    }

    public void deleteIssue(Issue issue) throws Exception {
        issueDao.deleteIssue(issue);
    }

    public List<Issue> getIssues() {
        return getIssues(new IssueFilter());
    }

    public List<Issue> getIssues(IssueFilter filter) {
        return getIssues(filter, -1, -1);
    }

    public List<Issue> getIssues(IssueFilter filter, int page, int numberPerPage) {
        return issueDao.getIssues(filter, page, numberPerPage);
    }

    public void setIssueDao(IssueDao issueDao) {
        this.issueDao = issueDao;
    }

}
