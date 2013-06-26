package org.patientview.radar.dao;

import org.patientview.radar.model.Issue;
import org.patientview.radar.model.filter.IssueFilter;

import java.util.List;

public interface IssueDao {

    Issue getIssue(Long id);

    void saveIssue(Issue issue) throws Exception;

    void deleteIssue(Issue issue) throws Exception;

    List<Issue> getIssues(IssueFilter filter, int page, int numberPerPage);

}
