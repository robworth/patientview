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

package org.patientview.batch;

import org.patientview.patientview.model.EmailQueue;
import org.patientview.patientview.model.Job;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.service.EmailQueueManager;
import org.patientview.service.JobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * According the status of every entry in EmailQueue,to re-set the jobs' status
 */
@Component
public class CheckSendEmailStatusReader extends ListItemReader<Object> {

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Autowired
    private JobManager jobManager;

    public void refresh() {

        List<Object> list = new ArrayList<Object>();
        List<Job> jobs = jobManager.getJobList(SendEmailEnum.SENT);
        List<EmailQueue> emailQueues;
        boolean isFailed = false;
        boolean hasSucceeded = false;
        boolean hasSending = false;

        for (Job job : jobs) {
            isFailed = false;
            hasSucceeded = false;
            hasSending = false;

            emailQueues = emailQueueManager.getEmailQueueList(job.getId());

            for (EmailQueue queue : emailQueues) {
                if (SendEmailEnum.SENDING.equals(queue.getStatus())) {
                    hasSending = true;
                    break;
                }
                if (SendEmailEnum.SUCCEEDED.equals(queue.getStatus())) {
                    hasSucceeded = true;
                }

                if (SendEmailEnum.FAILED.equals(queue.getStatus())) {
                    isFailed = true;
                    break;
                }
            }

            if (hasSending) {
                continue;
            }

            if (!emailQueues.isEmpty()) {
                if (hasSucceeded && isFailed) {
                    job.setStatus(SendEmailEnum.FAILED);
                    list.add(job);
                } else if (hasSucceeded && !isFailed) {
                    job.setStatus(SendEmailEnum.SUCCEEDED);
                    list.add(job);
                } else if (isFailed) {
                    job.setStatus(SendEmailEnum.FAILED);
                    list.add(job);
                }
            }
        }

        setList(list);
    }
}
