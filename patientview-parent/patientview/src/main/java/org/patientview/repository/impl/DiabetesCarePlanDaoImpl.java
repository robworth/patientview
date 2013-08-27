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

import org.patientview.patientview.model.DiabetesCarePlan;
import org.patientview.patientview.model.DiabetesCarePlan_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.DiabetesCarePlanDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "diabetesCarePlanDao")
public class DiabetesCarePlanDaoImpl extends AbstractHibernateDAO<DiabetesCarePlan> implements DiabetesCarePlanDao {

    @Override
    public DiabetesCarePlan get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DiabetesCarePlan> criteria = builder.createQuery(DiabetesCarePlan.class);
        Root<DiabetesCarePlan> carePlanRoot = criteria.from(DiabetesCarePlan.class);

        criteria.where(builder.equal(carePlanRoot.get(DiabetesCarePlan_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
