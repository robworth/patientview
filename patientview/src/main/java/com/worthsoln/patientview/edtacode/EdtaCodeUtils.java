package com.worthsoln.patientview.edtacode;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.Hibernate;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.HibernateUtil;

public class EdtaCodeUtils {

    public static EdtaCode retrieveEdtaCode(DatabaseDAO dao, String code) {
        return (EdtaCode) dao.retrieveItem(new EdtaCodeDao(new EdtaCode(code)));
    }

    public static void addEdtaCodeToRequest(String code, String attributeName, DatabaseDAO dao,
                                            HttpServletRequest request) {
        EdtaCode treatmentCode = EdtaCodeUtils.retrieveEdtaCode(dao, code);

        request.setAttribute(attributeName, treatmentCode);
        HibernateUtil.retrievePersistentObjectAndAddToRequestWithIdParameter(request, EdtaCode.class, code,
            attributeName);
    }

    public static List getCodeLinksForLinkType(String linkTypeMappingParameter) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List edtaCodes = session.find("from " + EdtaCode.class.getName()
                                      + " as code where code.linkType = ? order by code.edtaCode asc",
                                      linkTypeMappingParameter, Hibernate.STRING);

        tx.commit();
        HibernateUtil.closeSession();

        return edtaCodes;
    }
}
