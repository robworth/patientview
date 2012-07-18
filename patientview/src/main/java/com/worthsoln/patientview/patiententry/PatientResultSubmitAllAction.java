package com.worthsoln.patientview.patiententry;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.PatientUtils;
import com.worthsoln.patientview.TestResult;
import com.worthsoln.patientview.TestResultDao;
import com.worthsoln.patientview.model.Comment;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.utils.LegacySpringUtils;
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

        DatabaseDAO dao = new DatabaseDAO("patientview");

        String nhsno = PatientUtils.retrieveNhsNo(request);

        for (PatientEnteredResult patientResult : patientResults.values()) {
            addPatientEnteredResultToDatabase(dao, nhsno, patientResult.getResultCode1(),
                    patientResult.getValue1(), patientResult.getDatetime());
            if (!"".equals(patientResult.getResultCode2())) {
                addPatientEnteredResultToDatabase(dao, nhsno, patientResult.getResultCode2(),
                        patientResult.getValue2(), patientResult.getDatetime());
            }
        }

        session.removeAttribute(patientResultName);

        return mapping.findForward("success");
    }

    private void addPatientEnteredResultToDatabase(DatabaseDAO dao, String nhsno,
                                                   String testCode, String testValue, Calendar dateTime) {
        if ("resultcomment".equalsIgnoreCase(testCode)) {
            Comment comment = new Comment(dateTime, nhsno, testValue);
            LegacySpringUtils.getCommentManager().save(comment);
        } else {
            TestResult testResult = new TestResult(nhsno, UnitUtils.PATIENT_ENTERS_UNITCODE, dateTime, testCode, testValue);
            TestResultDao testResultDao = new TestResultDao(testResult);
            dao.insertItem(testResultDao);
        }
    }
}