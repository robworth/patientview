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

import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.SplashPage;
import org.patientview.patientview.model.SplashPage_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.SplashPageDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository(value = "splashPageDao")
public class SplashPageDaoImpl extends AbstractHibernateDAO<SplashPage> implements SplashPageDao {

    @Override
    public List<SplashPage> getAll(List<String> unitcodes, Specialty specialty) {

        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SplashPage> criteria = builder.createQuery(SplashPage.class);
        Root<SplashPage> from = criteria.from(SplashPage.class);
        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(from.get(SplashPage_.specialty), specialty));

        if (unitcodes != null && unitcodes.size() > 0) {
            wherePredicates.add(from.get(SplashPage_.unitcode).in(unitcodes.toArray(new String[unitcodes.size()])));
        }

        buildWhereClause(criteria, wherePredicates);
        return getEntityManager().createQuery(criteria).getResultList();
    }
}
