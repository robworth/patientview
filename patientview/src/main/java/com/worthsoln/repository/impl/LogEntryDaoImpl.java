package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.LogEntry;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.LogEntryDao;

import java.util.Calendar;
import java.util.List;

/**
 *
 */
public class LogEntryDaoImpl extends AbstractHibernateDAO<LogEntry> implements LogEntryDao {
    @Override
    public LogEntry getLatestLogEntry(String nhsno, String action) {

//        session = HibernateUtil.currentSession();
//        tx = session.beginTransaction();
//        criteria = session.createCriteria(LogEntry.class);
//        criteria.add(Expression.eq("nhsno", nhsno));
//        criteria.add(Expression.like("action", AddLog.PATIENT_DATA_FOLLOWUP));
//        criteria.addOrder(Order.desc("date"));
//        criteria.setMaxResults(1);

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<LogEntry> get(String username, Calendar startdate, Calendar enddate) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        Criteria criteria = session.createCriteria(LogEntry.class);
//        criteria.add(Expression.between("date", startdate, enddate));
//        criteria.add(Expression.eq("actor", username));
//        criteria.addOrder(Order.asc("id"));
//        logEntries = criteria.list();
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    // action is optional
    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, Calendar startdate, Calendar enddate, String action) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        Criteria criteria = session.createCriteria(LogEntry.class);
//        criteria.add(Expression.between("date", startdate, enddate));
//        criteria.add(Expression.like("nhsno", "%" + nhsno + "%"));
//        criteria.add(Expression.like("action", "%patient data%"));
//        criteria.addOrder(Order.asc("id"));
//        logEntries = criteria.list();
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, String user, String actor, String action, String unitcode, Calendar startdate, Calendar enddate) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        Criteria criteria = session.createCriteria(LogEntry.class);
//        criteria.add(Expression.between("date", startdate, enddate));
//        criteria.add(Expression.like("nhsno", "%" + nhsno + "%"));
//        criteria.add(Expression.like("user", "%" + user + "%"));
//        criteria.add(Expression.like("actor", "%" + actor + "%"));
//        criteria.add(Expression.like("action", "%" + action + "%"));
//        criteria.add(Expression.like("unitcode", "%" + unitcode + "%"));
//        criteria.addOrder(Order.asc("id"));
//        logEntries = criteria.list();
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;
    }

    @Override
    public List<LogEntry> getWithUnitCode(String unitcode, Calendar startdate, Calendar enddate) {

//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        Criteria criteria = session.createCriteria(LogEntry.class);
//        criteria.add(Expression.between("date", startdate, enddate));
//        criteria.add(Expression.like("unitcode", "%" + unitcode + "%"));
//        criteria.addOrder(Order.asc("id"));
//        logEntries = criteria.list();
//        tx.commit();
//        HibernateUtil.closeSession();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
