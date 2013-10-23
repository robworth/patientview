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

import org.patientview.patientview.model.Diagnosis;
import org.patientview.patientview.model.Diagnosis_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.DiagnosisDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "diagnosisDao")
public class DiagnosisDaoImpl extends AbstractHibernateDAO<Diagnosis> implements DiagnosisDao {

    @Override
    public List<Diagnosis> getOtherDiagnoses(String nhsno, String unitcode) {
        // when unit codes are stored they are put in uppercase
        unitcode = unitcode.toUpperCase();

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Diagnosis> criteria = builder.createQuery(Diagnosis.class);
        Root<Diagnosis> diagnosisRoot = criteria.from(Diagnosis.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(diagnosisRoot.get(Diagnosis_.nhsno), nhsno));
        wherePredicates.add(builder.equal(diagnosisRoot.get(Diagnosis_.unitcode), unitcode));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public void deleteOtherDiagnoses(String nhsno, String unitcode) {
        Query query = getEntityManager().createQuery(
                "DELETE FROM diagnosis WHERE nhsno = :nhsno AND unitcode = :unitcode");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
