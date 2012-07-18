package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.UnitStat;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.UnitStatDao;

import java.util.List;

/**
 *
 */
public class UnitStatDaoImpl extends AbstractHibernateDAO<UnitStat> implements UnitStatDao {
    @Override
    public List<UnitStat> get(String unitCode) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        List<UnitStat> unitStats =
//                session.find("from " + UnitStat.class.getName() + " unitstat where unitstat.unitcode = ?", unitcode,
//                        Hibernate.STRING);
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
