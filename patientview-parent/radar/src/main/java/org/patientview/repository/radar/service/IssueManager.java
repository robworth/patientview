package org.patientview.repository.radar.service;

import org.patientview.model.radar.Issue;
import org.patientview.model.radar.filter.IssueFilter;

import java.util.List;

public interface IssueManager {

    Issue getIssue(Long id);

    void saveIssue(Issue issue) throws Exception;

    void deleteIssue(Issue issue) throws Exception;

    List<Issue> getIssues();

    List<Issue> getIssues(IssueFilter filter);

    List<Issue> getIssues(IssueFilter filter, int page, int numberPerPage);    

}
