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

import org.patientview.ibd.model.medication.MyMedication_;
import org.patientview.ibd.model.medication.MyMedication;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.ibd.MyMedicationDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "myMedicationDao")
public class MyMedicationDaoImpl extends AbstractHibernateDAO<MyMedication> implements MyMedicationDao {

    @Override
    public List<MyMedication> getAllMedications(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedication> criteria = builder.createQuery(MyMedication.class);
        Root<MyMedication> myMedicineRoot = criteria.from(MyMedication.class);

        criteria.where(builder.equal(myMedicineRoot.get(MyMedication_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<MyMedication> getCurrentMedications(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedication> criteria = builder.createQuery(MyMedication.class);
        Root<MyMedication> myMedicineRoot = criteria.from(MyMedication.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(myMedicineRoot.get(MyMedication_.nhsno), nhsno));
        wherePredicates.add(builder.isNull(myMedicineRoot.get(MyMedication_.dateStopped)));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(myMedicineRoot.get(MyMedication_.dateStarted)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<MyMedication> getStoppedMedications(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedication> criteria = builder.createQuery(MyMedication.class);
        Root<MyMedication> myMedicineRoot = criteria.from(MyMedication.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(myMedicineRoot.get(MyMedication_.nhsno), nhsno));
        wherePredicates.add(builder.isNotNull(myMedicineRoot.get(MyMedication_.dateStopped)));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(myMedicineRoot.get(MyMedication_.dateStopped)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
