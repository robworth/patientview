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

import org.patientview.patientview.model.Letter;
import org.patientview.patientview.model.Unit;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.FeatureDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

@Repository(value = "featureDao")
public class FeatureDaoImpl extends AbstractHibernateDAO<Letter> implements FeatureDao {

    @Override
    public List<Unit> getUnitsForFeature(String feature) {

        if (null != feature && !"".equals(feature)) {
            String sql = "SELECT "
                    + "  u.*  "
                    + "FROM "
                    + "   unit u, "
                    + "   pv_feature_access f "
                    + "WHERE "
                    + "   f.unit_id = u.id "
                    + "AND "
                    + "   f.name = :featureName ";

            Query query = getEntityManager().createNativeQuery(sql, Unit.class);

            query.setParameter("featureName", feature);

            List<Unit> units = query.getResultList();

            return units;
        }

        return Collections.emptyList();
    }
}
