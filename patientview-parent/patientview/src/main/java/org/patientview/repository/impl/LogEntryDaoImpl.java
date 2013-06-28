/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.repository.impl;

import org.patientview.patientview.model.LogEntry;
import org.patientview.patientview.model.LogEntry_;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.LogEntryDao;
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
    public LogEntry getLatestLogEntry(String nhsno, String action) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<LogEntry> criteria = builder.createQuery(LogEntry.class);
        Root<LogEntry> logEntryRoot = criteria.from(LogEntry.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.nhsno), nhsno));
        wherePredicates.add(builder.equal(logEntryRoot.get(LogEntry_.action), action));

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

    /**
     * @param action all entries containing this action text is returned, ie. it's used as a pattern
     */
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
