/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.patientview;

import org.patientview.actionutils.ActionUtils;
import org.patientview.patientview.logon.LogonUtils;
import org.patientview.patientview.model.Comment;
import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.ResultHeading;
import org.patientview.patientview.model.TestResultWithUnitShortname;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.patientview.user.UserUtils;
import org.patientview.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestResultsAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        User user = UserUtils.retrieveUser(request);

        if (user != null) {
            request.setAttribute("user", user);

            Panel currentPanel = managePanels(request);

            List<TestResultWithUnitShortname> results = extractTestResultsWithComments(currentPanel, user);

            Collection<Result> resultsInRecords = turnResultsListIntoRecords(results);

            managePages(request, resultsInRecords);
            request.setAttribute("results", resultsInRecords);

            List<ResultHeading> resultsHeadingsList
                    = LegacySpringUtils.getResultHeadingManager().get(currentPanel.getPanel());

            request.setAttribute("resultsHeadings", resultsHeadingsList);
        } else if (!LegacySpringUtils.getSecurityUserManager().isRolePresent("patient")) {
            return LogonUtils.logonChecks(mapping, request, "control");
        }

        ActionUtils.setUpNavLink(mapping.getParameter(), request);

        return LogonUtils.logonChecks(mapping, request);
    }

    private List<TestResultWithUnitShortname> extractTestResultsWithComments(Panel currentPanel, User user) {
        List<TestResultWithUnitShortname> results
                = LegacySpringUtils.getTestResultManager().getTestResultForPatient(user, currentPanel);

        List userMappings = UserUtils.retrieveUserMappings(user);

        for (Object obj : userMappings) {
            UserMapping userMapping = (UserMapping) obj;
            addCommentsForNhsno(userMapping.getNhsno(), currentPanel, results);
        }

        return results;
    }

    private void addCommentsForNhsno(String nhsno, Panel currentPanel, List<TestResultWithUnitShortname> results) {

        // Comments (from the comments table) should show on the 1st panel of the results
        if (currentPanel.getPanel() == 1) {
            List<Comment> comments = LegacySpringUtils.getCommentManager().get(nhsno);

            for (Comment comment : comments) {
                results.add(new TestResultWithUnitShortname(nhsno, UnitUtils.PATIENT_ENTERS_UNITCODE,
                        comment.getDatestamp(),
                        "resultcomment", Long.toString(comment.getId()), UnitUtils.PATIENT_ENTERS_UNITCODE));
            }
        }
    }

    private Panel managePanels(HttpServletRequest request) {
        Panel currentPanel = currentPanel(request);
        List<Panel> panelList = LegacySpringUtils.getResultHeadingManager().getPanels();
        for (Panel p : panelList) {
            p.setResultHeadings(LegacySpringUtils.getResultHeadingManager().get(p.getPanel()));
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
            // provide a default if not set
            currentPanel = new Panel(1);
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

    // Note this means that if the user has multiple nhsno they will be ordered all together
    public int compareTo(Object o) {
        TestResultId resultToCompareThisTo = (TestResultId) o;
        if (dateStamped.equals(resultToCompareThisTo.getDateStamped())) {

            String compareToPrepost = resultToCompareThisTo.getPrepost() != null
                    ? resultToCompareThisTo.getPrepost() : "";

            String thisPrepost = this.prepost != null ? this.prepost : "";

            if (thisPrepost.equals(compareToPrepost)) {
                String compareToShortname = resultToCompareThisTo.getShortname() != null
                        ? resultToCompareThisTo.getShortname() : "";

                String thisShortname = this.shortname != null ? this.shortname : "";

                return thisShortname.compareToIgnoreCase(compareToShortname);
            } else {
                return thisPrepost.compareToIgnoreCase(compareToPrepost);
            }
        } else if (dateStamped.before(resultToCompareThisTo.getDateStamped())) {
            return 1;
        } else {
            return -1;
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
