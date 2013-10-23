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

import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.ResultHeading;
import org.patientview.patientview.model.ResultHeading_;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.ResultHeadingDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository(value = "resultHeadingDao")
public class ResultHeadingDaoImpl extends AbstractHibernateDAO<ResultHeading> implements ResultHeadingDao {

    @Override
    public ResultHeading get(String headingcode, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> from = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(ResultHeading_.headingcode), headingcode));

        buildWhereClause(criteria, wherePredicates);
        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<ResultHeading> getAll(Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> root = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(ResultHeading_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<ResultHeading> get(int panel, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ResultHeading> criteria = builder.createQuery(ResultHeading.class);
        Root<ResultHeading> root = criteria.from(ResultHeading.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(root.get(ResultHeading_.panel), panel));
        wherePredicates.add(builder.equal(root.get(ResultHeading_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(root.get(ResultHeading_.panelorder)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public void delete(String headingCode, Specialty specialty) {

        delete(get(headingCode, specialty));
    }

    @Override
    public List<Panel> getPanels(Specialty specialty) {

        String sql = "SELECT DISTINCT panel FROM result_heading WHERE panel != 0 ORDER BY panel ASC";

        Query query = getEntityManager().createNativeQuery(sql);

        List resultsObjs = query.getResultList();
        List<Panel> results = new ArrayList<Panel>();
        for (Object object : resultsObjs) {
            results.add(new Panel((Integer) object));
        }

        return results;
    }
}
