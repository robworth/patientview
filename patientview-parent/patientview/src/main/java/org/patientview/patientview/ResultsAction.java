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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.patientview.actionutils.ActionUtils;
import org.patientview.patientview.logon.LogonUtils;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.TestResultWithUnitShortname;
import org.patientview.patientview.model.ResultHeading;
import org.patientview.patientview.user.UserUtils;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ResultsAction extends Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultsAction.class);

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {

        User user = UserUtils.retrieveUser(request);
        List<String> resultCodes = new ArrayList<String>();
        String resultType1 = ActionUtils.retrieveStringPropertyValue("resultType1", form, request);
        String resultType2 = ActionUtils.retrieveStringPropertyValue("resultType2", form, request);
        String monthBeforeNow = ActionUtils.retrieveStringPropertyValue("period", form, request);

        if (StringUtils.isNotEmpty(resultType1) || StringUtils.isNotEmpty(resultType2)) {
            resultCodes.add(resultType1);
            resultCodes.add(resultType2);

            // default show two years testresult data
            if (StringUtils.isEmpty(monthBeforeNow)) {
                monthBeforeNow = "24";
            }

            if (user != null) {
                request.setAttribute("user", user);

                List<TestResultWithUnitShortname> results
                        = LegacySpringUtils.getTestResultManager().getTestResultForPatient(user, resultCodes,
                        monthBeforeNow);

                if (!results.isEmpty()) {

                    Collection<Result> resultsInRecords = turnResultsListIntoRecords(results);
                    String jsonData = convertToJsonData(resultsInRecords, resultType1, resultType2);
                    try {
                        PrintWriter printWriter = response.getWriter();
                        printWriter.write(jsonData);
                        printWriter.flush();
                        printWriter.close();

                    } catch (Exception e) {
                        LOGGER.debug("Couldn't wring json data fro testresult graphing" + e.getMessage());
                    }
                }

            } else if (!LegacySpringUtils.getSecurityUserManager().isRolePresent("patient")) {
                return LogonUtils.logonChecks(mapping, request, "control");
            }
        }

        return null;
    }

    private String convertToJsonData(Collection<Result> resultData, String resultType1, String resultType2) {

        String resultValue1 = "";
        String resultValue2 = "";

        ResultHeading heading1 = LegacySpringUtils.getResultHeadingManager().get(resultType1);
        ResultHeading heading2 = LegacySpringUtils.getResultHeadingManager().get(resultType2);

        StringBuffer sb = new StringBuffer();
        // cols header
        sb.append("{\"cols\":[");
        // DateTime
        sb.append("{\"id\":\"DateTime\",\"label\":\"DateTime\",\"type\":\"string\"},");

        if (StringUtils.isNotEmpty(resultType1)) {
            // result type 1
            sb.append("{\"id\":\"").append(heading1.getHeading()).append("\",");
            sb.append("\"label\":\"").append(heading1.getHeading()).append("\",");
            sb.append("\"type\":\"number\"},");
            // tooltip for result type 1
            sb.append("{\"id\":\"\",");
            sb.append("\"role\":\"tooltip\",");
            sb.append("\"type\":\"string\",");
            sb.append("\"p\":{\"role\":\"tooltip\",\"html\":\"true\"}}");

            if (StringUtils.isEmpty(resultType2)) {
                sb.append("],");
            } else {
                sb.append(",");
            }
        }

        if (StringUtils.isNotEmpty(resultType2)) {

            // result type 2
            sb.append("{\"id\":\"").append(heading2.getHeading()).append("\",");
            sb.append("\"label\":\"").append(heading2.getHeading()).append("\",");
            sb.append("\"type\":\"number\"},");
            // tooltip result type 2
            sb.append("{\"id\":\"\",");
            sb.append("\"role\":\"tooltip\",");
            sb.append("\"type\":\"string\",");
            sb.append("\"p\":{\"role\":\"tooltip\",\"html\":\"true\"}}],");
        }

        // rows value
        sb.append("\"rows\":[");

        for (Iterator iterator = resultData.iterator(); iterator.hasNext();) {
            Result result = (Result) iterator.next();
            resultValue1 = result.getValue(resultType1);
            resultValue2 = result.getValue(resultType2);

            sb.append("{\"c\":[");
            // DateTime
            sb.append("{\"v\":\"").append(dateFormat.format(result.getTimeStamp().getTime())).append("\"},");

            if (StringUtils.isNotEmpty(resultType1)) {
                // result type 1
                sb.append("{\"v\":\"").append(resultValue1).append("\"},");
                // tooltip for result type 1
                sb.append("{\"v\":\"").append(getHtmlTooltip(result, heading1, resultValue1));
                sb.append("\"}");

                if (StringUtils.isNotEmpty(resultType2)) {
                    sb.append(",");
                }
            }

            if (StringUtils.isNotEmpty(resultType2)) {
                // column 3: result type 2
                sb.append("{\"v\":\"").append(resultValue2).append("\"},");
                // tooltip for result type 2
                sb.append("{\"v\":\"").append(getHtmlTooltip(result, heading2, resultValue2)).append("\"}");
            }

            if (iterator.hasNext()) {
                sb.append("]},");
            } else {
                sb.append("]}");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

    private String getHtmlTooltip(Result result, ResultHeading heading, String value) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div style='font-size: 15px;font-family:Arial;'>");
        // result type and value
        stringBuffer.append("<div style='padding:8px 5px;'>");
        stringBuffer.append("<a href='").append(heading.getLink()).append("' target='_blank'>");
        stringBuffer.append(heading.getHeading()).append(": </a>");
        stringBuffer.append("<strong>").append(value).append("</strong></div>");

        // datetime
        stringBuffer.append("<div style='padding:0px 5px 8px;'><strong>");
        stringBuffer.append(result.getFormattedTimeStamp()).append("</strong></div></div>");

        return stringBuffer.toString();
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

