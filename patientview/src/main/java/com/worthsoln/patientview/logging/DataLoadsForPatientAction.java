package com.worthsoln.patientview.logging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.unit.UnitUtils;

public class DataLoadsForPatientAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Calendar startdate = LoggingUtils.getDefaultStartDateForLogQuery();
        Calendar enddate = LoggingUtils.getDefaultEndDateForLogQuery();
        String nhsno = BeanUtils.getProperty(form, "nhsno");
        List log = getLogEntries(nhsno, startdate, enddate);
        request.setAttribute("log", log);
        UnitUtils.putRelevantUnitsInRequest(request);
        LoggingUtils.defaultDatesInForm(form, startdate, enddate);
        BeanUtils.setProperty(form, "action", AddLog.PATIENT_DATA_FOLLOWUP);
        return LogonUtils.logonChecks(mapping, request);
    }

    private List getLogEntries(String nhsno, Calendar startdate, Calendar enddate) throws HibernateException {
        List logEntries = new ArrayList();
        if (nhsno != null && !nhsno.equals("")) {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(LogEntry.class);
            criteria.add(Expression.between("date", startdate, enddate));
            criteria.add(Expression.like("nhsno", "%" + nhsno + "%"));
            criteria.add(Expression.like("action", "%patient data%"));
            criteria.addOrder(Order.asc("id"));
            logEntries = criteria.list();
            tx.commit();
            HibernateUtil.closeSession();
        }
        return logEntries;
    }
}
