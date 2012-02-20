package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.service.IssueManager;
import com.solidstategroup.radar.model.Issue;
import com.solidstategroup.radar.model.filter.IssueFilter;
import com.solidstategroup.radar.dao.IssueDao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
