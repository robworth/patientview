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

import org.patientview.patientview.model.News;
import org.patientview.patientview.model.News_;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.NewsDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *      Note: if there is possibility of the news being shown in a public context, i.e. not logged in,
 *      then the method will null check the specialty and only use the specialty if defined.
 */
@Repository(value = "newsDao")
public class NewsDaoImpl extends AbstractHibernateDAO<News> implements NewsDao {

    @Override
    public List<News> getAll(Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        if (specialty != null) {
            wherePredicates.add(builder.equal(from.get(News_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<News> getNewsForEveryone(Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(News_.everyone), true));

        if (specialty != null) {
            wherePredicates.add(builder.equal(from.get(News_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<News> getAdminNewsForUnitCodes(List<String> unitCodes, Specialty specialty) {

        unitCodes.add("all");   // some oddness to override stuff

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);

        Predicate unitCodePredicate = from.get(News_.unitcode).in(unitCodes.toArray(new String[unitCodes.size()]));
        Predicate adminPredicate = builder.equal(from.get(News_.admin), true);
        Predicate patientPredicate = builder.equal(from.get(News_.patient), true);
        Predicate everyonePredicate = builder.equal(from.get(News_.everyone), true);
        Predicate specialtyPredicate = builder.equal(from.get(News_.specialty), specialty);

        Predicate adminOrPatientPredicate =  getEntityManager().getCriteriaBuilder()
                .or(adminPredicate, patientPredicate);

        Predicate securedNews = getEntityManager().getCriteriaBuilder()
                .and(unitCodePredicate, adminOrPatientPredicate);

        Predicate fullPredicate = getEntityManager().getCriteriaBuilder()
                .or(securedNews, everyonePredicate);

        Predicate fullPredicateWithSpecialty = getEntityManager().getCriteriaBuilder()
                .and(fullPredicate, specialtyPredicate);

        criteria.where(fullPredicateWithSpecialty);

        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<News> getAdminEditNewsForUnitCodes(List<String> unitCodes, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);

        Predicate unitCodePredicate = from.get(News_.unitcode).in(unitCodes.toArray(new String[unitCodes.size()]));
        Predicate adminPredicate = builder.equal(from.get(News_.admin), true);
        Predicate patientPredicate = builder.equal(from.get(News_.patient), true);
        Predicate specialtyPredicate = builder.equal(from.get(News_.specialty), specialty);

        Predicate adminOrPatientPredicate =  getEntityManager().getCriteriaBuilder()
                .or(adminPredicate, patientPredicate);

        Predicate securedNews = getEntityManager().getCriteriaBuilder()
                .and(unitCodePredicate, adminOrPatientPredicate, specialtyPredicate);

        criteria.where(securedNews);

        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<News> getPatientNewsForUnitCodes(List<String> unitCodes, Specialty specialty) {

        unitCodes.add("all");   // some oddness to override stuff

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<News> criteria = builder.createQuery(News.class);
        Root<News> from = criteria.from(News.class);

        Predicate unitCodePredicate = from.get(News_.unitcode).in(unitCodes.toArray(new String[unitCodes.size()]));
        Predicate patientPredicate = builder.equal(from.get(News_.patient), true);
        Predicate everyonePredicate = builder.equal(from.get(News_.everyone), true);
        Predicate specialtyPredicate = builder.equal(from.get(News_.specialty), specialty);

        Predicate securedNews = getEntityManager().getCriteriaBuilder()
                .and(unitCodePredicate, patientPredicate);

        Predicate fullPredicate = getEntityManager().getCriteriaBuilder()
                .or(securedNews, everyonePredicate);

        Predicate fullPredicateWithSpecialty = getEntityManager().getCriteriaBuilder()
                .and(fullPredicate, specialtyPredicate);

        criteria.where(fullPredicateWithSpecialty);

        criteria.orderBy(builder.desc(from.get(News_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
