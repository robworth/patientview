package com.worthsoln.patientview.diagnosis;

import java.util.List;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.Type;
import com.worthsoln.HibernateUtil;

public class DiagnosisUtils {

    public static List getOtherDiagnoses(String nhsno, String unitcode) throws HibernateException {
        Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        List diagnoses = session.find("from " + Diagnosis.class.getName() +
                " as diagnosis where diagnosis.nhsno = ? AND diagnosis.unitcode = ? " +
                " order by diagnosis.displayorder asc", new Object[]{nhsno, unitcode},
                new Type[]{Hibernate.STRING, Hibernate.STRING});
        tx.commit();
        HibernateUtil.closeSession();
        return diagnoses;
    }
}
