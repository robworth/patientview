package org.patientview.radar.test.dao;

import org.patientview.radar.dao.IssueDao;
import org.patientview.radar.model.Issue;
import org.patientview.radar.model.enums.IssuePriority;
import org.patientview.radar.model.enums.IssueStatus;
import org.patientview.radar.model.enums.IssueType;
import org.patientview.radar.model.filter.IssueFilter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class IssueDaoTest extends BaseDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueDaoTest.class);

    @Autowired
    private IssueDao issueDao;

        @Test
    public void testGetIssue() {
        Issue issue = issueDao.getIssue(1L);
        assertNotNull(issue);
    }

    @Test
    public void testGetIssues() {
        List<Issue> issues = issueDao.getIssues(new IssueFilter(), -1, -1);
        assertNotNull(issues);
        assertTrue(issues.size() > 0);
    }

    @Test
    public void testGetIssuesPage1() {
        List<Issue> issues = issueDao.getIssues(new IssueFilter(), 1, 1);
        assertNotNull(issues);
        assertTrue(issues.size() == 1);
    }

    @Test
    public void testSearchIssues() {
        IssueFilter issueFilter = new IssueFilter();
        issueFilter.addSearchCriteria(IssueFilter.Field.STATUS.getDatabaseFieldName(), IssueStatus.CLOSED.getName());
        List<Issue> issues = issueDao.getIssues(issueFilter, -1, -1);
        assertNotNull(issues);
        assertTrue(issues.size() > 0);
    }

    @Test
    public void testSaveIssue() throws Exception {
        Issue issue = new Issue();
        issue.setType(IssueType.CORRECTION);
        issue.setPage("Test page");
        issue.setDescription("Test description");
        issue.setDescription("Test description");
        issue.setComments("Test comment");
        issue.setPriority(IssuePriority.HIGH);
        issue.setStatus(IssueStatus.OPEN);

        // Save
        issueDao.saveIssue(issue);

        // Make sure we have an ID and a date registered
        assertTrue("Saved issue doesn't have an ID", issue.getId() > 0);

        // Try and get the patient user - should get our new user
        issue = issueDao.getIssue(issue.getId());
        assertNotNull("Saved issue was null on getting from DAO", issue);
    }

    @Test
    public void testSaveExistingIssue() throws Exception {
        Issue issue = issueDao.getIssue(1L);
        issue.setStatus(IssueStatus.CLOSED);

        issueDao.saveIssue(issue);

        issue = issueDao.getIssue(1L);
        assertTrue("Issue status has not been updated", issue.getStatus().equals(IssueStatus.CLOSED));
    }

    @Test
    public void testDeleteIssue() throws Exception {
        issueDao.deleteIssue(issueDao.getIssue(1L));
        Issue issue = issueDao.getIssue(1L);
        assertNull("Issue was found after being deleted", issue);
    }

}
