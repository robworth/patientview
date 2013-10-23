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

import org.patientview.ibd.model.Procedure;
import org.patientview.ibd.model.Procedure_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.ibd.ProcedureDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "procedureDao")
public class ProcedureDaoImpl extends AbstractHibernateDAO<Procedure> implements ProcedureDao {

    @Override
    public Procedure getProcedure(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Procedure> criteria = builder.createQuery(Procedure.class);
        Root<Procedure> procedureRoot = criteria.from(Procedure.class);

        criteria.where(builder.equal(procedureRoot.get(Procedure_.nhsno), nhsno));
        criteria.orderBy(builder.asc(procedureRoot.get(Procedure_.date)));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        if (nhsno == null || nhsno.length() == 0) {
            throw new IllegalArgumentException("nhsno and unitcode are required parameter to delete procedure");
        }

        Query query = getEntityManager().createQuery(
                "DELETE FROM pv_procedure WHERE nhsno = :nhsno AND unitcode = :unitcode");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
