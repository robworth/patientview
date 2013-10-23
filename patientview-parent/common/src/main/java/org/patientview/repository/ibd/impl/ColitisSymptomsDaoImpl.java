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

package org.patientview.repository.ibd.impl;

import org.patientview.ibd.model.symptoms.ColitisSymptoms;
import org.patientview.ibd.model.symptoms.ColitisSymptoms_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.ibd.ColitisSymptomsDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository(value = "colitisDao")
public class ColitisSymptomsDaoImpl extends AbstractHibernateDAO<ColitisSymptoms> implements ColitisSymptomsDao {

    @Override
    public void save(ColitisSymptoms colitisSymptoms) {
        colitisSymptoms.setScore(colitisSymptoms.calculateScore());
        super.save(colitisSymptoms);
    }

    @Override
    public List<ColitisSymptoms> getAllColitis(String nhsno, Date fromDate, Date toDate) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ColitisSymptoms> criteria = builder.createQuery(ColitisSymptoms.class);
        Root<ColitisSymptoms> colitisRoot = criteria.from(ColitisSymptoms.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(colitisRoot.get(ColitisSymptoms_.nhsno), nhsno));

        if (fromDate != null && toDate != null) {
            wherePredicates.add(builder.between(colitisRoot.get(ColitisSymptoms_.symptomDate), fromDate, toDate));
        } else if (fromDate != null) {
            wherePredicates.add(builder.greaterThan(colitisRoot.get(ColitisSymptoms_.symptomDate), fromDate));
        } else if (toDate != null) {
            wherePredicates.add(builder.greaterThan(colitisRoot.get(ColitisSymptoms_.symptomDate), toDate));
        }

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.desc(colitisRoot.get(ColitisSymptoms_.symptomDate)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
