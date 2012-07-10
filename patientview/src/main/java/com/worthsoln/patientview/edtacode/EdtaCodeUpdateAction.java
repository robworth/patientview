package com.worthsoln.patientview.edtacode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.database.action.DatabaseAction;

public class EdtaCodeUpdateAction extends DatabaseAction {

    public ActionForward execute(
        ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        EdtaCode edtaCode = new EdtaCode();

        BeanUtils.setProperty(edtaCode, "edtaCode", BeanUtils.getProperty(form, "edtaCode"));

        String[] colNames = HibernateUtil.getPropertyNames(edtaCode.getClass());

        for (int i = 0; i < colNames.length; i++) {
            BeanUtils.setProperty(edtaCode, colNames[i], BeanUtils.getProperty(form, colNames[i]));
        }

        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(edtaCode);
        tx.commit();
        HibernateUtil.closeSession();
        request.setAttribute("edtaCode", edtaCode);
        HibernateUtil.retrievePersistentObjectAndAddToRequest(request, EdtaCode.class, EdtaCode.getIdentifier());

        return LogonUtils.logonChecks(mapping, request);
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "edtaCode";
    }
}
