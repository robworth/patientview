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

import org.patientview.patientview.model.EmailQueue;
import org.patientview.repository.job.EmailQueueDao;
import org.patientview.service.EmailQueueManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value = "emailQueueManager")
public class EmailQueueManagerImpl implements EmailQueueManager {

    @Inject
    private EmailQueueDao emailQueueDao;


    @Override
    public void save(EmailQueue emailQueue) throws Exception {
        if (!emailQueue.hasValidId()) {
           emailQueue.setCreated(new Date());
        }
        emailQueueDao.save(emailQueue);
    }

    @Override
    public List<EmailQueue> getEmailQueueList() {
        return emailQueueDao.getEmailQueueList();
    }

    @Override
    public List<EmailQueue> getEmailQueueList(Long jobId) {
        return emailQueueDao.getEmailQueueList(jobId);
    }

    @Override
    public EmailQueue get(Long jobId, Long messageId, Long userId) {
        return emailQueueDao.get(jobId, messageId, userId);
    }
}
