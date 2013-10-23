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

import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.MyIbd_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.ibd.MyIbdDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "myIbdDao")
public class MyIbdDaoImpl extends AbstractHibernateDAO<MyIbd> implements MyIbdDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyIbdDaoImpl.class);

    @Override
    public MyIbd get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyIbd> criteria = builder.createQuery(MyIbd.class);
        Root<MyIbd> myIbdRoot = criteria.from(MyIbd.class);

        criteria.where(builder.equal(myIbdRoot.get(MyIbd_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NonUniqueResultException ex) {
            // Bad situation if this is a , the model cannot handle the unit data.
            // too late to throw an exception, log and close your eyes.
            LOGGER.error("Found multiple MyIbds for nhsno {}, returning null", nhsno);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error getting MyIbds for nhsno {}, returning null, {}", nhsno, e.getMessage());
            return null;
        }
    }

    @Override
    public MyIbd get(String nhsno, String unitcode) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyIbd> criteria = builder.createQuery(MyIbd.class);
        Root<MyIbd> myIbdRoot = criteria.from(MyIbd.class);

        criteria.where(builder.equal(myIbdRoot.get(MyIbd_.nhsno), nhsno),
                builder.equal(myIbdRoot.get(MyIbd_.unitcode), unitcode));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NonUniqueResultException ex) {
            // Bad situation if this is a , the model cannot handle the unit data.
            // too late to throw an exception, log and close your eyes.
            LOGGER.error("Found multiple MyIbds for nhsno {} and unitcode {}, returning null", nhsno, unitcode);
            return null;
        } catch (NoResultException e) {
            // No entity found for query - this should be ok
            return null;
        }
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        if (nhsno == null || nhsno.length() == 0 || unitcode == null || unitcode.length() == 0) {
            throw new IllegalArgumentException("nhsno and unitcode are required parameter to delete myIbd");
        }

        MyIbd myIbd = get(nhsno, unitcode);

        if (myIbd != null) {
            delete(myIbd);
        }
    }
}
