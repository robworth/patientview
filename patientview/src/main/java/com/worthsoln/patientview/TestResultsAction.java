package com.worthsoln.patientview;

import com.worthsoln.actionutils.ActionUtils;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.Comment;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.patientview.resultheading.ResultHeadingDao;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class TestResultsAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        DatabaseDAO dao = getDao(request);
        User user = UserUtils.retrieveUser(request);
        if (user != null) {
            request.setAttribute("user", user);

            Panel currentPanel = managePanels(request, dao);
            List<TestResultWithUnitShortname> results = extractTestResultsWithComments(dao, currentPanel, user);
            Collection<Result> resultsInRecords = turnResultsListIntoRecords(results);
            managePages(request, resultsInRecords);
            request.setAttribute("results", resultsInRecords);

            List<ResultHeading> resultsHeadingsList = dao.retrieveList(new ResultHeadingDao(currentPanel));
            request.setAttribute("resultsHeadings", resultsHeadingsList);
        } else if (!LegacySpringUtils.getSecurityUserManager().isRolePresent("patient")) {
            return LogonUtils.logonChecks(mapping, request, "control");
        }
        ActionUtils.setUpNavLink(mapping.getParameter(), request);
        return LogonUtils.logonChecks(mapping, request);
    }

    private List<TestResultWithUnitShortname> extractTestResultsWithComments(DatabaseDAO dao,
                                                                             Panel currentPanel, User user) {
        TestResultForPatientDao resultDao = new TestResultForPatientDao(user.getUsername(), currentPanel);
        List<TestResultWithUnitShortname> results = dao.retrieveList(resultDao);

        List userMappings = UserUtils.retrieveUserMappings(user);

        for (Object obj : userMappings) {
            UserMapping userMapping = (UserMapping) obj;
            addCommentsForNhsno(userMapping.getNhsno(), currentPanel, results);
        }

        return results;
    }

    private void addCommentsForNhsno(String nhsno, Panel currentPanel, List<TestResultWithUnitShortname> results) {

        // Note: This seems to be trying to do something with the panel and result headings.
        // We have removed because it did appear to do anything.

        List<Comment> comments = LegacySpringUtils.getCommentManager().get(nhsno);

        for (Comment comment : comments) {
            results.add(new TestResultWithUnitShortname(nhsno, UnitUtils.PATIENT_ENTERS_UNITCODE, comment.getDatestamp(),
                    "resultcomment", Long.toString(comment.getId()), UnitUtils.PATIENT_ENTERS_UNITCODE));
        }
    }

    private Panel managePanels(HttpServletRequest request, DatabaseDAO dao) {
        Panel currentPanel = currentPanel(request);
        List<Panel> panelList = dao.retrieveList(new PanelsDao());
        for (Panel p : panelList) {
            p.setResultHeadings(dao.retrieveList(new ResultHeadingDao(p)));
        }
        PanelNavigation panelNav = new PanelNavigation(currentPanel, panelList);
        request.setAttribute("panelNav", panelNav);
        return currentPanel;
    }

    private void managePages(HttpServletRequest request, Collection<Result> resultsInRecords) {
        Panel currentPage = currentPage(request);
        int resultsPerPage = resultsPerPage(request);
        int numberOfResults = resultsInRecords.size();
        int numberOfPages = (int) Math.ceil((1.0 * numberOfResults) / (1.0 * resultsPerPage));
        List<Panel> pageList = createPages(numberOfPages);
        PanelNavigation pageNav = new PanelNavigation(currentPage, pageList);
        request.setAttribute("pages", pageList);
        request.setAttribute("numberOfPages", numberOfPages + "");
        request.setAttribute("resultsPerPage", resultsPerPage);
        request.setAttribute("resultsOffset", (currentPage.getPanel() - 1) * resultsPerPage);
        request.setAttribute("pageNav", pageNav);
    }

    private int resultsPerPage(HttpServletRequest request) {
        ServletContext servletContext = request.getSession().getServletContext();
        String resultsPerPageString = servletContext.getInitParameter("default.results.per.page");
        return Integer.parseInt(resultsPerPageString);
    }

    private List<Panel> createPages(int numberOfPages) {
        List<Panel> pages = new ArrayList<Panel>();
        for (int i = 1; i <= numberOfPages; i++) {
            Panel page = new Panel(i);
            pages.add(page);
        }
        return pages;
    }

    private Panel currentPanel(HttpServletRequest request) {
        Panel currentPanel = null;
        try {
            currentPanel = new Panel(Integer.parseInt(request.getParameter("panel")));
        } catch (Exception e) {
            ;
        }
        return currentPanel;
    }

    private Panel currentPage(HttpServletRequest request) {
        Panel currentPage = null;
        try {
            currentPage = new Panel(Integer.parseInt(request.getParameter("page")));
        } catch (Exception e) {
            currentPage = new Panel(1);
        }
        return currentPage;
    }

    private Collection<Result> turnResultsListIntoRecords(List<TestResultWithUnitShortname> resultsList) {
        Map<TestResultId, Result> resultsRecords = new TreeMap<TestResultId, Result>();
        for (TestResultWithUnitShortname testResult : resultsList) {
            TestResultId testResultId = new TestResultId(testResult);
            Result result = resultsRecords.get(testResultId);
            if (result == null) {
                result = new Result(testResult);
                resultsRecords.put(testResultId, result);
            }
            result.addResult(testResult.getTestcode(), testResult.getValue());
        }
        return resultsRecords.values();
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "edtaCode";
    }
}

class TestResultId implements Comparable {

    private String nhsno;
    private Calendar dateStamped;
    private String prepost;
    private String shortname;

    public TestResultId(TestResultWithUnitShortname testResult) {
        this.nhsno = testResult.getNhsno();
        this.prepost = testResult.getPrepost();
        this.dateStamped = testResult.getDatestamped();
        this.shortname = testResult.getShortname();
    }

    public int compareTo(Object o) {
        TestResultId resultToCompareThisTo = (TestResultId) o;
        if (nhsno.equals(resultToCompareThisTo.getNhsno())) {
            if (dateStamped.equals(resultToCompareThisTo.getDateStamped())) {
                if (prepost.equals(resultToCompareThisTo.getPrepost())) {
                    return shortname.compareToIgnoreCase(resultToCompareThisTo.getShortname());
                } else {
                    return prepost.compareToIgnoreCase(resultToCompareThisTo.getPrepost());
                }
            } else if (dateStamped.before(resultToCompareThisTo.getDateStamped())) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return nhsno.compareToIgnoreCase(resultToCompareThisTo.getNhsno());
        }
    }

    public String getNhsno() {
        return nhsno;
    }

    public String getPrepost() {
        return prepost;
    }

    public Calendar getDateStamped() {
        return dateStamped;
    }

    public String getShortname() {
        return shortname;
    }
}