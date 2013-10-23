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
package org.patientview.repository.impl.job;

import org.patientview.patientview.model.Job;
import org.patientview.patientview.model.Job_;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.job.JobDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * JobDao implement Class
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository(value = "jobDao")
public class JobDaoImpl extends AbstractHibernateDAO<Job> implements JobDao {

    @Override
    public void save(Job job) {
        if (!job.hasValidId()) {
            job.setCreated(new Date());
        }

        super.save(job);
    }

    @Override
    public List<Job> getJobList(SendEmailEnum status) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Job> criteria = builder.createQuery(Job.class);
        Root<Job> jobRoot = criteria.from(Job.class);

        criteria.where(builder.equal(jobRoot.get(Job_.status), status));

        criteria.orderBy(builder.asc(jobRoot.get(Job_.created)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

}
