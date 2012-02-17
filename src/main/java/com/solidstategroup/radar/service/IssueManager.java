package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.Issue;
import com.solidstategroup.radar.model.filter.IssueFilter;

import java.util.List;

public interface IssueManager {

    Issue getIssue(Long id);

    void saveIssue(Issue issue) throws Exception;

    void deleteIssue(Issue issue) throws Exception;

    List<Issue> getIssues();

    List<Issue> getIssues(IssueFilter filter);

    List<Issue> getIssues(IssueFilter filter, int page, int numberPerPage);    

}
