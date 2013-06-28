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

package org.patientview.patientview.patiententry;

import org.patientview.patientview.PatientUtils;
import org.patientview.patientview.model.TestResult;
import org.patientview.patientview.model.Comment;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Map;

public class PatientResultSubmitAllAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        String patientResultName = BeanUtils.getProperty(form, "patientResultName");
        HttpSession session = request.getSession();
        Map<Long, PatientEnteredResult> patientResults =
                (Map<Long, PatientEnteredResult>) session.getAttribute(patientResultName);

        String nhsno = PatientUtils.retrieveNhsNo(request);

        for (PatientEnteredResult patientResult : patientResults.values()) {
            addPatientEnteredResultToDatabase(nhsno, patientResult.getResultCode1(),
                    patientResult.getValue1(), patientResult.getDatetime());
            if (!"".equals(patientResult.getResultCode2())) {
                addPatientEnteredResultToDatabase(nhsno, patientResult.getResultCode2(),
                        patientResult.getValue2(), patientResult.getDatetime());
            }
        }

        session.removeAttribute(patientResultName);

        return mapping.findForward("success");
    }

    private void addPatientEnteredResultToDatabase(String nhsno,
                                                   String testCode, String testValue, Calendar dateTime) {
        if ("resultcomment".equalsIgnoreCase(testCode)) {
            Comment comment = new Comment(dateTime, nhsno, testValue);
            LegacySpringUtils.getCommentManager().save(comment);
        } else {
            TestResult testResult = new TestResult(nhsno, UnitUtils.PATIENT_ENTERS_UNITCODE, dateTime, testCode,
                    testValue);
            LegacySpringUtils.getTestResultManager().save(testResult);
        }
    }
}
