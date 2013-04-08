package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.LogEntry;
import com.worthsoln.patientview.model.LogEntry_;
import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.LogEntryDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository(value = "logEntryDao")
public class LogEntryDaoImpl extends AbstractHibernateDAO<LogEntry> implements LogEntryDao {

    @Override
    public LogEntry getLatestLogEntry(String nhsno, String action, Specialty specialty) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LogEntry> criteria = builder.createQuery(LogEntry.class);
        Root<LogEntry> logEntryRoot = criteria.from(LogEntry.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.nhsno), nhsno));
        wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.action), action));

        if (specialty != null) {
            // this could be extended to show data from other specialty...
            wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.desc(logEntryRoot.get(LogEntry_.date)));

        // want only the latest one back
        Query query = getEntityManager().createQuery(criteria);
        query.setMaxResults(1);

        try {
            return (LogEntry) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<LogEntry> get(String username, Calendar startdate, Calendar enddate, Specialty specialty) {
        return getLogEntries(null, null, username, null, null, startdate, enddate, specialty);
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, Calendar startdate, Calendar enddate, String action,
                                       Specialty specialty) {
        return getLogEntries(nhsno, null, null, null, action, startdate, enddate, specialty);
    }

    @Override
    public List<LogEntry> getWithNhsNo(String nhsno, String user, String actor, String action, String unitcode,
                                       Calendar startdate, Calendar enddate, Specialty specialty) {
        return getLogEntries(nhsno, user, actor, unitcode, action, startdate, enddate, specialty);
    }

    @Override
    public List<LogEntry> getWithUnitCode(String unitcode, Calendar startdate, Calendar enddate, Specialty specialty) {
        return getLogEntries(null, null, null, unitcode, null, startdate, enddate, specialty);
    }

    private List<LogEntry> getLogEntries(String nhsno, String user, String actor, String unitcode, String action,
                                         Calendar startdate, Calendar enddate, Specialty specialty) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LogEntry> criteria = builder.createQuery(LogEntry.class);
        Root<LogEntry> logEntryRoot = criteria.from(LogEntry.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        if (nhsno != null && nhsno.length() > 0) {
            wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.nhsno), nhsno));
        }

        if (user != null && user.length() > 0) {
            wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.user), user));
        }

        if (actor != null && actor.length() > 0) {
            wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.actor), actor));
        }

        if (unitcode != null && unitcode.length() > 0) {
            unitcode = unitcode.toUpperCase(); // this is stored in uppercase
            wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.unitcode), unitcode));
        }

        if (action != null && action.length() > 0) {
            // TODO: not sure if this really has to be a like it all seemed very dodgy
            wherePredicates.add(builder.like(logEntryRoot.get(LogEntry_.action), '%' + action + '%'));
        }

        if (startdate != null && enddate != null) {
            wherePredicates.add(builder.between(logEntryRoot.get(LogEntry_.date), startdate, enddate));
        }

        // pull back results that have the current specialty or none
        Predicate specialtyPred = builder.equal(logEntryRoot.get(LogEntry_.specialty), specialty);
        Predicate specialtyNone = builder.isNull(logEntryRoot.get(LogEntry_.specialty));

        Predicate specialtyOrNone = builder.or(specialtyPred, specialtyNone);

        wherePredicates.add(specialtyOrNone);

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(logEntryRoot.get(LogEntry_.id)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
