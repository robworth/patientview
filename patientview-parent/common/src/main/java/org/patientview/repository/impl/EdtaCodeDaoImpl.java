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

import org.patientview.patientview.model.EdtaCode;
import org.patientview.patientview.model.EdtaCode_;
import org.patientview.patientview.model.Specialty;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.EdtaCodeDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "edtaCodeDao")
public class EdtaCodeDaoImpl extends AbstractHibernateDAO<EdtaCode> implements EdtaCodeDao {

    @Override
    public EdtaCode getEdtaCode(String edtaCode, Specialty specialty) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EdtaCode> criteria = builder.createQuery(EdtaCode.class);
        Root<EdtaCode> edtaCodeRoot = criteria.from(EdtaCode.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.edtaCode), edtaCode));
        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.specialty), specialty));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(String edtaCode, Specialty specialty) {
        EdtaCode edtaCodeToRemove = getEdtaCode(edtaCode, specialty);

        if (edtaCodeToRemove != null) {
            delete(edtaCodeToRemove);
        }
    }

    @Override
    public List<EdtaCode> get(String linkType, Specialty specialty) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EdtaCode> criteria = builder.createQuery(EdtaCode.class);
        Root<EdtaCode> edtaCodeRoot = criteria.from(EdtaCode.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.linkType), linkType));

        // if not specialty show all codes for all specialties (i.e. the user is not logged in
        // this may need changing in future
        if (specialty != null) {
            wherePredicates.add(builder.equal(edtaCodeRoot.get(EdtaCode_.specialty), specialty));
        }

        buildWhereClause(criteria, wherePredicates);
        criteria.orderBy(builder.asc(edtaCodeRoot.get(EdtaCode_.edtaCode)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
