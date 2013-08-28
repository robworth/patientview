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

import org.patientview.patientview.model.FootCheckup;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.FootCheckupDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository(value = "footCheckupDao")
public class FootCheckupDaoImpl extends AbstractHibernateDAO<FootCheckup> implements FootCheckupDao {

    @Override
    public List<FootCheckup> get(String userName) {
        if (null != userName && !"".equals(userName)) {
            String sql = "SELECT DISTINCT dia_footcheckup.* FROM dia_footcheckup, usermapping "
                    + "WHERE dia_footcheckup.nhsno = usermapping.nhsno "
                    + "AND usermapping.username = :username ORDER BY dia_footcheckup.footCheckDate DESC";

            Query query = getEntityManager().createNativeQuery(sql, FootCheckup.class);

            query.setParameter("username", userName);

            return query.getResultList();

        }
        return null;
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        if (nhsno == null || nhsno.length() == 0) {
            throw new IllegalArgumentException("nhsno and unitcode are required parameter to delete checkups");
        }

        Query query = getEntityManager().createQuery(
                "DELETE FROM FootCheckup WHERE nhsno = :nhsno AND unitcode = :unitcode");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
