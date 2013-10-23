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

import org.patientview.patientview.model.Diagnostic;
import org.patientview.patientview.model.Diagnostic_;
import org.patientview.patientview.model.enums.DiagnosticType;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.DiagnosticDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Repository(value = "diagnosticDao")
public class DiagnosticDaoImpl extends AbstractHibernateDAO<Diagnostic> implements DiagnosticDao {

    @Override
    public Diagnostic get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Diagnostic> criteria = builder.createQuery(Diagnostic.class);
        Root<Diagnostic> diagnosticRoot = criteria.from(Diagnostic.class);

        criteria.where(builder.equal(diagnosticRoot.get(Diagnostic_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NonUniqueResultException ex) {
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Diagnostic> get(Set<String> nhsNos, DiagnosticType diagnosticType) {

        // nhsNos and diagnosticType are required params
        if (nhsNos == null || nhsNos.size() == 0 || diagnosticType == null) {
            return new ArrayList<Diagnostic>();
        }

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Diagnostic> criteria = builder.createQuery(Diagnostic.class);
        Root<Diagnostic> from = criteria.from(Diagnostic.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(from.get(Diagnostic_.nhsno).in(nhsNos));
        wherePredicates.add(builder.equal(from.get(Diagnostic_.diagnosticTypeId), diagnosticType.getId()));

        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.asc(from.get(Diagnostic_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        if (nhsno == null || nhsno.length() == 0) {
            throw new IllegalArgumentException("nhsno and unitcode are required parameter to delete diagnostic");
        }

        Query query = getEntityManager().createQuery(
                "DELETE FROM diagnostic WHERE nhsno = :nhsno AND unitcode = :unitcode");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
