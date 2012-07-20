package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.EdtaCodeDao;

import java.util.List;

/**
 *
 */
public class EdtaCodeDaoImpl extends AbstractHibernateDAO<EdtaCode> implements EdtaCodeDao {
    @Override
    public void delete(String edtaCode) {


//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//
//        session.delete(new EdtaCode(edtaCodeId));
//        tx.commit();
//        HibernateUtil.closeSession();
    }

    @Override
    public List<EdtaCode> get(String linkType) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        List edtaCodes = session.find("from " + EdtaCode.class.getName()
//                + " as code where code.linkType = ? order by code.edtaCode asc",
//                linkTypeMappingParameter, Hibernate.STRING);
//
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
