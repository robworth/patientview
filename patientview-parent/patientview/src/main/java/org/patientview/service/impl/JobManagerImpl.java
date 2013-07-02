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

package org.patientview.service.impl;

import org.patientview.patientview.model.Job;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.Unit;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.repository.UserDao;
import org.patientview.repository.job.JobDao;
import org.patientview.service.JobManager;
import org.patientview.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value = "jobManager")
public class JobManagerImpl implements JobManager {

    @Inject
    private JobDao jobDao;

    @Autowired
    private UserManager userManager;

    @Inject
    private UserDao userDao;

    @Override
    public void save(Job job) {
        jobDao.save(job);
    }

    @Override
    public List<Job> getJobList(SendEmailEnum status) {
        return jobDao.getJobList(status);
    }

    @Override
    public List<User> getSpecialGroupUsers(User user, Specialty specialty, String userType, Unit unit) {
        return userDao.get(user, specialty, userType, unit);
    }
}
