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
import org.patientview.ibd.model.MyIbdSeverityLevel;
import org.patientview.ibd.model.MyIbdSeverityLevel_;
import org.patientview.ibd.model.enums.Diagnosis;
import org.patientview.ibd.model.enums.Severity;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.ibd.MyIbdDao;
import org.patientview.repository.ibd.MyIbdSeverityLevelDao;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "myIbdSeverityLevelDao")
public class MyIbdSeverityLevelDaoImpl extends AbstractHibernateDAO<MyIbdSeverityLevel>
        implements MyIbdSeverityLevelDao {

    @Inject
    private MyIbdDao myIbdDao;

    @Override
    public MyIbdSeverityLevel get(String nhsno, Severity severity) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyIbdSeverityLevel> criteria = builder.createQuery(MyIbdSeverityLevel.class);
        Root<MyIbdSeverityLevel> myIbdSeverityLevelRoot = criteria.from(MyIbdSeverityLevel.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(myIbdSeverityLevelRoot.get(MyIbdSeverityLevel_.nhsno), nhsno));
        wherePredicates.add(builder.equal(myIbdSeverityLevelRoot.get(MyIbdSeverityLevel_.severityId),
                severity.getId()));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            MyIbd myIbd = myIbdDao.get(nhsno);
            Diagnosis diagnosis = myIbd.getDiagnosis();

            return new MyIbdSeverityLevel(nhsno, severity, severity.getDefaultLevel(diagnosis), null);
        }
    }

    @Override
    public void save(List<MyIbdSeverityLevel> myIbdSeverityLevels) {
        if (myIbdSeverityLevels != null) {
            for (MyIbdSeverityLevel myIbdSeverityLevel : myIbdSeverityLevels) {
                save(myIbdSeverityLevel);
            }
        }
    }

    @Override
    public void save(MyIbdSeverityLevel myIbdSeverityLevel) {
        // need to test if there is a record with this level already and just use that
        MyIbdSeverityLevel existingMyIbdSeverityLevel = get(myIbdSeverityLevel.getNhsno(),
                myIbdSeverityLevel.getSeverity());

        if (existingMyIbdSeverityLevel.hasValidId()) {
            if (canSave(myIbdSeverityLevel)) {
                if (levelChanged(myIbdSeverityLevel)) {
                    MyIbd myIbd = myIbdDao.get(myIbdSeverityLevel.getNhsno());
                    Diagnosis diagnosis = myIbd.getDiagnosis();

                    existingMyIbdSeverityLevel.setLevel(myIbdSeverityLevel.getLevel(diagnosis));
                }

                super.save(existingMyIbdSeverityLevel);

                myIbdSeverityLevel.setId(existingMyIbdSeverityLevel.getId());
            } else {
                delete(existingMyIbdSeverityLevel);
            }
        } else {
            // if the new value is > 0 and not the default then save
            if (canSave(myIbdSeverityLevel)) {
                if (!levelChanged(myIbdSeverityLevel)) {
                    myIbdSeverityLevel.setLevel(null);
                }

                super.save(myIbdSeverityLevel);
            }
        }
    }

    /**
     * Only wanna save if it actually has values else we will just have a load of empty rows
     * @param myIbdSeverityLevel MyIbdSeverityLevel
     * @return boolean
     */
    private boolean canSave(MyIbdSeverityLevel myIbdSeverityLevel) {
        return levelChanged(myIbdSeverityLevel) || (myIbdSeverityLevel.getTreatment() != null
                && myIbdSeverityLevel.getTreatment().length() > 0);
    }

    /**
     * will check if the value has actually been updated and is not the default
     * @param myIbdSeverityLevel MyIbdSeverityLevel
     * @return boolean
     */
    private boolean levelChanged(MyIbdSeverityLevel myIbdSeverityLevel) {
        MyIbd myIbd = myIbdDao.get(myIbdSeverityLevel.getNhsno());
        Diagnosis diagnosis = myIbd.getDiagnosis();

        return (myIbdSeverityLevel.getLevel(diagnosis) > 0
                && myIbdSeverityLevel.getLevel(diagnosis) != myIbdSeverityLevel.getSeverity().getDefaultLevel(
                diagnosis));
    }
}
