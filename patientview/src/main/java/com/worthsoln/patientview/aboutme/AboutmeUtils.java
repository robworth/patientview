package com.worthsoln.patientview.aboutme;

import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.user.UserUtils;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

public class AboutmeUtils {

    static Aboutme fetchAboutmeForPatient(User user) throws HibernateException {

        UserMapping userMapping = UserUtils.retrieveUserMappingsPatientEntered(user);

        Aboutme aboutme = null;

        if (userMapping != null) {
            String nhsno = userMapping.getNhsno();

            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();

            aboutme = (Aboutme) session.createQuery("from " + Aboutme.class.getName() + " as aboutme where aboutme.nhsno = :nhsno ")
                    .setString("nhsno", nhsno)
                    .uniqueResult();

            tx.commit();
            HibernateUtil.closeSession();
        }
        return aboutme;
    }
}
